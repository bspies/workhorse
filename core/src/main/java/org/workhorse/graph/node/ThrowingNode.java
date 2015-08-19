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
package org.workhorse.graph.node;

import org.workhorse.event.Event;
import org.workhorse.event.thrower.Thrower;
import org.workhorse.exec.Execution;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;

/**
 * Node that generates an event.
 *
 * @author  Brennan Spies
 */
public class ThrowingNode<E extends Event> extends BaseEventNode
{
	private Thrower<E> thrower;
	
	/**
	 * Default constructor
	 * @param parent The graph owning this node
	 * @param lane The swimlane this node belongs to
	 * @param thrower The event trigger for firing the event
	 */
	public ThrowingNode(Graph parent, Lane lane, Thrower<E> thrower) {
		super(parent, lane);
		this.thrower = thrower;
	}
	
	/**
	 * @see org.workhorse.graph.node.BaseEventNode#doEvent(org.workhorse.exec.Execution)
	 */
	public void doEvent(Execution execution) {
		thrower.throwEvent(execution);
	}
}
