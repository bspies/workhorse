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

import org.workhorse.event.handler.Handler;
import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;

/**
 * @author Brennan Spies
 * 
 * <p>Represents a starting event node.</p>
 */
public class StartNode extends BaseSourceNode implements StartingNode, EventNode
{
	private Handler<?> handler;
	
	/**
	 * A start node with no event.
	 * @param parent The graph parent
	 * @param lane The lane to which the node belongs
	 */
	public StartNode(Graph parent, Lane lane) {
		super(parent, lane);
	}
	
	/**
	 * A start node with an event.
	 * @param parent The graph parent
	 * @param lane The lane to which the node belongs
	 * @param handler The event handler
	 */
	public StartNode(Graph parent, Lane lane, Handler<?> handler) {
		this(parent, lane);
		this.handler = handler;
	}
	
	/**
	 * Starts an execution.
	 */
	public void start(Execution execution) {
		onEntering(execution);
		if(handler!=null)
			doEvent(execution);
		if(!execution.isPaused()) {
			onLeaving(execution);
			leave(execution);
		}
	}

	/**
	 * @see org.workhorse.graph.node.EventNode#doEvent(org.workhorse.exec.Execution)
	 */
	public void doEvent(Execution execution) {
		
	}

	/**
	 * @see org.workhorse.graph.node.Node#reenter(org.workhorse.exec.Execution, org.workhorse.exec.Phase)
	 */
	public void reenter(Execution execution, Phase phase) {
		if(phase==Phase.LEAVE) {
			leave(execution);
		} else {
			throw new IllegalArgumentException("Invalid reentry phase for start node: " + phase);
		}
	}
}
