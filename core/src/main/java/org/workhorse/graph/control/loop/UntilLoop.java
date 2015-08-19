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
package org.workhorse.graph.control.loop;

import org.workhorse.expr.Condition;
import org.workhorse.graph.control.NodeControl;
import org.workhorse.sequence.SequenceBuilder;
import org.workhorse.sequence.impl.LoopSequence;

/**
 * @author  Brennan Spies  <p>Control that specifies a loop that is evaluated at the end of the sequence.</p>
 */
public class UntilLoop implements NodeControl 
{
	/**
	 * @uml.property  name="loopCondition"
	 * @uml.associationEnd  
	 */
	private final Condition loopCondition;
	
	public UntilLoop(Condition condition) {
		this.loopCondition = condition;
	}
	
	/**
	 * @see org.workhorse.graph.control.NodeControl#getBuilder()
	 */
	public SequenceBuilder getBuilder() {
		return LoopSequence.newBuilder(loopCondition, false);
	}
}
