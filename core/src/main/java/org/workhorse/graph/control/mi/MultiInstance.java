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
import org.workhorse.graph.control.NodeControl;

/**
 * @author  Brennan Spies
 */
public abstract class MultiInstance implements NodeControl
{	
	/**
	 * @uml.property  name="instanceCount"
	 * @uml.associationEnd  
	 */
	private Expression<Integer> instanceCount;
	
	public MultiInstance(Expression<Integer> instanceCount) {
		this.instanceCount = instanceCount;
	}
	
	/**
	 * Returns the expression that will determine the number of instances of the activity to create at runtime.
	 * @return  The instance count expression
	 * @uml.property  name="instanceCount"
	 */
	public Expression<Integer> getInstanceCount() {
		return instanceCount;
	}
}