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

import org.workhorse.event.Cancellation;
import org.workhorse.event.Trigger;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.Execution;
import org.workhorse.graph.node.Node;

/**
 * {@link Thrower} that cancels a running transaction.
 *
 * @author  Brennan Spies
 */
public class Cancel implements Thrower<Cancellation> 
{
	private final Trigger<Cancellation> trigger;
	
	/**
	 * Constructor that omits reason for cancellation.
	 * @param node The node throwing the cancellation
	 */
	public Cancel(final Node node) {
		this(node, null);
	}
	
	/**
	 * Constructor that takes owning node and the reason
	 * for the cancellation.
	 * @param node The node throwing the cancellation
	 * @param reason The reason for the cancellation
	 */
	public Cancel(final Node node, final String reason) {
		trigger = new Trigger<Cancellation>() {
			public Cancellation generate(Context context) {
				return new Cancellation(node, reason);
			}
		};
	}
	
	/**
	 * @see org.workhorse.event.thrower.Thrower#throwEvent(org.workhorse.exec.Execution)
	 */
	public void throwEvent(Execution execution) {
		Cancellation cancellation = 
			trigger.generate(execution.getExecutionContext());
		//TODO throwEvent()
	}
}
