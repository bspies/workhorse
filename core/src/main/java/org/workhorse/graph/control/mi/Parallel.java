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

import org.workhorse.expr.Constant;
import org.workhorse.expr.Expression;
import org.workhorse.sequence.SequenceBuilder;

/**
 * @author Brennan Spies
 */
public class Parallel extends MultiInstance 
{
	private Expression<Integer> gateCount;
	
	/**
	 * 
	 * @param instanceCount The instance count
	 * @param miGate The multi-instance gate
	 */
	public Parallel(Expression<Integer> instanceCount, Gate miGate) {
		super(instanceCount);
		switch(miGate) {
			case ALL:
				gateCount = instanceCount;
				break;
			case ONE:
				gateCount = new Constant<Integer>(1);
				break;
			case NONE:
				gateCount = new Constant<Integer>(0);
				break;
		}
	}
	
	/**
	 * @param instanceCount
	 * @param gateCount
	 */
	public Parallel(Expression<Integer> instanceCount, Expression<Integer> gateCount) {
		super(instanceCount);
		this.gateCount = gateCount;
	}
	
	/**
	 * Returns the expression ("gate count") indicating how many instances are allowed to proceed from the node.
	 * @return  The gate count expression
	 * @uml.property  name="gateCount"
	 */
	public Expression<Integer> getGateCount() {
		return gateCount;
	}

	/**
	 * @see org.workhorse.graph.control.NodeControl#getBuilder()
	 */
	public SequenceBuilder getBuilder() {
		// TODO build parallel multi-instance steps
		return null;
	}
}
