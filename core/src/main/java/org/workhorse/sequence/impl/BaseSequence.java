/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.workhorse.sequence.impl;

import java.util.LinkedList;
import java.util.Map;

import org.workhorse.exec.Execution;
import org.workhorse.sequence.SequenceBuilder;
import org.workhorse.sequence.Processor;
import org.workhorse.sequence.Sequence;
import org.workhorse.sequence.Step;
import org.workhorse.struct.ArrayStack;
import org.workhorse.struct.Stack;
import org.workhorse.util.Maps;

/**
 * A general-purpose base implementation of {@link Sequence}.
 *
 * @author Brennan Spies
 */
public abstract class BaseSequence implements Sequence 
{
	private LinkedList<Step> steps;
	private Map<Enum<?>,StepEntry> labels;
	private BaseSequence parent;
	
	public BaseSequence() {
		steps = new LinkedList<Step>();
		labels = Maps.newHashMap();
	}
	
	/**
	 * @see org.workhorse.sequence.Sequence#length()
	 */
	public int length() {
		return steps.size();
	}
	
	//returns the given labelled step, if present
	protected Step getStep(Enum<?> label) {
		if(labels.get(label)==null)
			return null;
		return labels.get(label).step;
	}

	//for subclasses to access sequence
	protected LinkedList<Step> getSteps() {
		return steps;
	}
	
	//for subclasses to access labels
	protected Map<Enum<?>,StepEntry> getLabels() {
		return labels;
	}
	
	//sets the parent sequence (if this is sub-sequence)
	protected void setParent(BaseSequence sequence) {
		parent = sequence;
	}
	
	//returns the parent sequence (if this is sub-sequence)
	protected BaseSequence getParent() { return parent; }
	
	//returns the processor for the sequence
	protected abstract Processor getProcessor();
	
	//returns the processor for the sequence starting at the given label
	protected Processor getProcessor(Enum<?> label) {
		return getProcessor(labels.get(label).getStep());
	}
	
	//returns a processor at the end of the sequence
	protected abstract Processor getProcessorAtEnd();
	
	//returns the processor for the sequence starting at the given step
	protected abstract Processor getProcessor(Step step);

	/**
	 * @see org.workhorse.sequence.Step#perform(org.workhorse.exec.Execution)
	 */
	public boolean perform(Execution execution) {
		Processor processor = getProcessor();
		Step nextStep = null;
		while((nextStep=processor.next(execution.getExecutionContext()))!=null) {
			if(!nextStep.perform(execution))
				return false;
		}
		return true;
	}

	/**
	 * @see org.workhorse.sequence.Sequence#reenter(java.lang.Enum, org.workhorse.exec.Execution)
	 */
	public boolean reenter(Enum<?> label, Execution execution) {
		if(labels.get(label)==null)
			throw new IllegalArgumentException("Label does not exist: " + label);
		Stack<Processor> stack = getProcessorStack(label);
		while(stack.size()>0) {
			Processor processor = stack.pop();
			Step nextStep = null;
			while((nextStep=processor.next(execution.getExecutionContext()))!=null) {
				if(!nextStep.perform(execution))
					return false;
			}
		}
		return true;
	}
	
	//constructs the Processor stack needed to continue execution
	private Stack<Processor> getProcessorStack(Enum<?> label) 
	{	
		BaseSequence current = labels.get(label).getSequence();
		LinkedList<BaseSequence> seqs = new LinkedList<BaseSequence>();
		do {
			seqs.add(current);
		} while ((current=current.getParent())!=null);
		
		//create processor and reverse onto stack
		Stack<Processor> stack = new ArrayStack<Processor>();
		while(seqs.size()>0) {
			current = seqs.removeLast();
			Processor processor;
			if(stack.size()==0) {
				processor = current.getProcessor(label);
			} else {
				//get processor that will continue at the next step
				LinkedList<Step> steps = current.getParent().getSteps();
				int i = steps.indexOf(current);
				if(i<0) 
					throw new IllegalStateException("Cannot find position of nested sequence");
				else if(steps.getLast()==current)
					processor = getProcessorAtEnd();
				else
					processor = getProcessor(steps.get(i+1));
				processor = current.getParent().getProcessor(current);
			}
			stack.push(processor);
		}
		return stack;
	}
	
	/*
	 * For referencing (potentially nested) steps in the
	 * sequence.
	 */
	protected static class StepEntry 
	{
		StepEntry(Step step, BaseSequence parent) {
			this.step = step;
			this.sequence = parent;
		}
		
		private Step step;
		private BaseSequence sequence;

		public Step getStep() { return step; }
		public BaseSequence getSequence() { return sequence; }
	}
	
	/*
	 * Abstract base builder.
	 */
	protected static abstract class BaseBuilder implements SequenceBuilder 
	{	
		private BaseSequence sequence;
		private boolean frozen = false;
		
		protected BaseBuilder(BaseSequence sequence) {
			this.sequence = sequence;
		}
		
		protected BaseSequence getSequence() { return sequence; }
		
		protected void freeze() { frozen = true; }
		
		/**
		 * @see org.workhorse.sequence.SequenceBuilder#add(org.workhorse.sequence.Step)
		 */
		public SequenceBuilder add(Step step) {
			checkMutability();
			//if step is a "jumpable" sequence
			if(step instanceof BaseSequence) {
				BaseSequence subSeq = (BaseSequence)step;
				subSeq.parent = sequence;
				migrateLabels(subSeq);
			}
			sequence.getSteps().add(step);
			return this;
		}

		/**
		 * @see org.workhorse.sequence.SequenceBuilder#add(java.lang.Enum, org.workhorse.sequence.Step)
		 */
		public SequenceBuilder add(Enum<?> label, Step step) {
			add(step);
			checkDuplicateLabel(label);
			sequence.getLabels().put(label, new StepEntry(step, sequence));
			return this;
		}
		
		//migrates labels from a subsequence to the sequence
		private void migrateLabels(BaseSequence from) {
			for(Enum<?> label : from.getLabels().keySet()) {
				checkDuplicateLabel(label);
				sequence.getLabels().put(label, from.getLabels().get(label));
			}
			from.getLabels().clear();
		}
		
		//check for duplicate label
		private void checkDuplicateLabel(Enum<?> label) {
			if(sequence.getLabels().containsKey(label))
				throw new IllegalArgumentException("Cannot add duplicate label: " + label);
		}
		
		//checks to see if build() has already been called
		private void checkMutability() {
			if(frozen) 
				throw new IllegalStateException("Cannot modify sequence after build() has been called");
		}
	}
}
