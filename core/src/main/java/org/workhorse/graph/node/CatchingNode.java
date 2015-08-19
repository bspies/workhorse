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

import org.workhorse.event.EventManager;
import org.workhorse.event.ThrownEvent;
import org.workhorse.event.handler.Catcher;
import org.workhorse.exec.Execution;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;

/**
 * Node that contains a  {@link Catcher} for catching thrown events.
 *
 * @author  Brennan Spies
 */
public class CatchingNode<E extends ThrownEvent<E>> extends BaseEventNode
{
	private final Catcher<E> catcher;
	private EventManager eventMgr;
	
	/**
	 * Constructs a catching node with the graph parent, lane to which the node
	 * belongs, and a catcher.
	 * @param parent The graph parent
	 * @param lane The lane to which the node belongs
	 * @param catcher The catcher
	 */
	public CatchingNode(Graph parent, Lane lane, Catcher<E> catcher) {
		super(parent, lane);
		this.catcher = catcher;
	}
	
	/**
	 * Returns the catcher for this node.
	 * @return The catcher
	 */
	public Catcher<E> getCatcher() { return catcher; }
	
	/**
	 * Receives an incoming event, placing it in
	 * the queue until it is retrieved by an {@link Execution}.
	 * @param event The incoming event
	 */
	public void receiveEvent(E event) {
		eventMgr.notifyReceived(this, event);
	}
	
	/**
	 * If no event, execution is paused. Otherwise, it is handled.
	 * @see org.workhorse.graph.node.BaseEventNode#doEvent(org.workhorse.exec.Execution)
	 */
	public void doEvent(Execution execution) {
		eventMgr.consumeOrPause(this, execution);
	}
}
