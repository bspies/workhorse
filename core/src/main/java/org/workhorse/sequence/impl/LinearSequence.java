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
import org.workhorse.sequence.Processor;
import org.workhorse.sequence.Sequence;
import org.workhorse.sequence.Step;

/**
 * @author Brennan Spies
 * <p>Implementation of {@link Sequence} that is processed in a linear fashion.</p>
 */
public class LinearSequence extends BaseSequence 
{
	private LinearSequence() {}
	
	/**
	 * @see org.workhorse.sequence.impl.BaseSequence#getProcessor()
	 */
	@Override protected Processor getProcessor() {
		return new LinearProcessor();
	}
	
	/**
	 * @see org.workhorse.sequence.impl.BaseSequence#getProcessor(org.workhorse.sequence.Step)
	 */
	@Override protected Processor getProcessor(Step step) {
		return new LinearProcessor(step);
	}
	
	/**
	 * @see org.workhorse.sequence.impl.BaseSequence#getProcessorAtEnd()
	 */
	@Override protected Processor getProcessorAtEnd() {
		return new LinearProcessor(getSteps().size());
	}
	
	/**
	 * Factory method to get sequence builder.
	 * @return The sequence builder
	 */
	public static org.workhorse.sequence.SequenceBuilder newBuilder() {
		return new Builder(new LinearSequence());
	}
	
	/**
	 * Simple linear processor that iterates over steps
	 */
	private class LinearProcessor implements Processor 
	{
		private final ListIterator<Step> iterator;
		
		private LinearProcessor() {
			iterator = getSteps().listIterator();
		}
		
		private LinearProcessor(int index) {
			iterator = getSteps().listIterator(index);
		}
		
		private LinearProcessor(Step step) {
			this(getSteps().indexOf(step));
		}
		
		public Step next(Context context) {
			return iterator.hasNext() ? iterator.next() : null;
		}
	}
	
	/**
	 * Builder for linear sequence.
	 */
	private static class Builder extends BaseBuilder 
	{
		private Builder(LinearSequence sequence) {
			super(sequence);
		}

		public Sequence build() {
			freeze();
			return getSequence();
		}
		
	}
}
