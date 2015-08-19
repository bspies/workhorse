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
package org.workhorse.event.thrower;

import org.workhorse.event.Compensation;
import org.workhorse.event.Trigger;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.Execution;
import org.workhorse.graph.node.Node;

/**
 * Throws a compensation event.
 *
 * @author  Brennan Spies
 */
public class Compensate implements Thrower<Compensation> 
{
	private final Trigger<Compensation> trigger;
	
	public Compensate(final Node node) {
		trigger = new Trigger<Compensation>() {
			public Compensation generate(Context context) {
				return new Compensation(node);
			}
		};
	}
	
	/**
	 * @see org.workhorse.event.thrower.Thrower#throwEvent(org.workhorse.exec.Execution)
	 */
	public void throwEvent(Execution execution) {
		// TODO throwEvent()
		Compensation compensation = 
			trigger.generate(execution.getExecutionContext());
	}
}
