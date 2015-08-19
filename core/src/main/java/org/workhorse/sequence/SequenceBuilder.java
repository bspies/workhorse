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
package org.workhorse.sequence;

import org.workhorse.util.Builder;

/**
 * @author Brennan Spies
 * 
 * <p>Interface for a sequence builder.</p>
 */
public interface SequenceBuilder extends Builder<Sequence>
{
	/**
	 * Adds a step in the sequence.
	 * @param step The step to add
	 * @return The builder instance
	 */
	public SequenceBuilder add(Step step);
	
	/**
	 * Adds a step to the sequence with the given label (for jumps).
	 * @param label The step label
	 * @param step The step
	 * @return The builder instance
	 */
	public SequenceBuilder add(Enum<?> label, Step step);
}
