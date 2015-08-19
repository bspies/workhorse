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
package org.workhorse.graph.control.mi;

import org.workhorse.expr.Expression;
import org.workhorse.sequence.SequenceBuilder;

/**
 * @author Brennan Spies
 * <p>Represents a multi-instance control that executes in a serial fashion (i.e., a loop with a fixed number
 * of iterations).</p>
 */
public class Serial extends MultiInstance 
{
	public Serial(Expression<Integer> instanceCount) {
		super(instanceCount);
	}

	/**
	 * @see org.workhorse.graph.control.NodeControl#getBuilder()
	 */
	public SequenceBuilder getBuilder() {
//		Sequence seq = new FixedLoop(getInstanceCount());
//		for(Step s : steps)
//			seq.addStep(s);
//		return seq;
		return null; //FIXME
	}
}
