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

import java.util.ListIterator;

import org.workhorse.exec.ctx.Context;
import org.workhorse.expr.Condition;
import org.workhorse.sequence.SequenceBuilder;
import org.workhorse.sequence.Processor;
import org.workhorse.sequence.Sequence;
import org.workhorse.sequence.Step;

/**
 * @author Brennan Spies
 * <p>Implementation of {@link Sequence} that loops. Specifically, the {@link Processor} will
 * loop if the loop condition evaluates to true in the current context.</p>
 */
public class LoopSequence extends BaseSequence 
{
	private final Condition loopCondition;
	private final boolean evalFirst;
	
	private LoopSequence(Condition loopCondition, boolean evalFirst) {
		this.loopCondition = loopCondition;
		this.evalFirst = evalFirst;
	}
	
	/**
	 * @see org.workhorse.sequence.impl.BaseSequence#getProcessor()
	 */
	@Override protected Processor getProcessor() {
		return evalFirst ? new WhileLoopProcessor() : new UntilLoopProcessor();
	}

	/**
	 * @see org.workhorse.sequence.impl.BaseSequence#getProcessor(org.workhorse.sequence.Step)
	 */
	@Override protected Processor getProcessor(Step step) {
		return evalFirst ? new WhileLoopProcessor(step) : new UntilLoopProcessor(step);
	}

	/**
	 * @see org.workhorse.sequence.impl.BaseSequence#getProcessorAtEnd()
	 */
	@Override protected Processor getProcessorAtEnd() {
		return evalFirst ? new WhileLoopProcessor(getSteps().size()) : 
			new UntilLoopProcessor(getSteps().size());
	}
	
	/**
	 * Returns a new sequence builder for building the loop.
	 * @param loopCondition The loop condition
	 * @param evalFirst True if "while" loop (condition evaluated first), false if "until" loop (condition evaluated last)
	 * @return The builder
	 */
	public static SequenceBuilder newBuilder(Condition loopCondition, boolean evalFirst) {
		return new LoopBuilder(new LoopSequence(loopCondition, evalFirst));
	}
	
	/**
	 * Builder for looping sequence.
	 */
	private static class LoopBuilder extends BaseBuilder 
	{
		LoopBuilder(LoopSequence sequence) {
			super(sequence);
		}

		public Sequence build() {
			freeze();
			return getSequence();
		}
	}
	
	/**
	 * A loop sequence processor that evaluates the loop condition
	 * at the end of the sequence.
	 */
	private class UntilLoopProcessor implements Processor 
	{
		private ListIterator<Step> iterator;
		
		UntilLoopProcessor() {
			iterator = getSteps().listIterator();
		}
		
		UntilLoopProcessor(int index) {
			iterator = getSteps().listIterator(index);
		}
		
		UntilLoopProcessor(Step step) {
			this(getSteps().indexOf(step));
		}
		
		/**
		 * @see org.workhorse.sequence.Processor#next(org.workhorse.exec.ctx.Context)
		 */
		public Step next(Context context) {
			if(!iterator.hasNext()) {
				if(!getSteps().isEmpty() && loopCondition.evaluate(context))
					iterator = getSteps().listIterator();	//loops
				else
					return null;	//finished looping
			}
			return iterator.next();
		}
	}
	
	/**
	 *  A loop sequence processor that evaluates the loop condition
	 *  at the beginning of the sequence.
	 */
	private class WhileLoopProcessor implements Processor
	{
		private ListIterator<Step> iterator;
		
		WhileLoopProcessor() {
			iterator = getSteps().listIterator();
		}
		
		WhileLoopProcessor(int index) {
			iterator = getSteps().listIterator(index);
		}
		
		WhileLoopProcessor(Step step) {
			this(getSteps().indexOf(step));
		}
		
		public Step next(Context context) {
			//at beginning
			if(iterator.nextIndex()==0) {
				if(getSteps().isEmpty() || !loopCondition.evaluate(context))
					return null;	//finished looping
			} else if(!iterator.hasNext()) {
				iterator = getSteps().listIterator();	//loops at end
				if(!loopCondition.evaluate(context))	//eval loop at beginning
					return null;
			}
			return iterator.next();
		}
		
	}
}
