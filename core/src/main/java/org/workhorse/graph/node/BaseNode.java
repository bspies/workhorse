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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workhorse.exec.Execution;
import org.workhorse.graph.Graph;
import org.workhorse.graph.GraphElement;
import org.workhorse.graph.Lane;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract base class for all nodes.
 *
 * @author Brennan Spies
 */
public abstract class BaseNode extends GraphElement implements Node
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Graph parent;
	private final Lane lane;
	private Collection<NodeListener> listeners;
	
	/**
	 * Constructor takes parent graph and lane to which the node
	 * belongs.
	 * @param parent The parent graph
	 * @param lane The lane
	 */
	public BaseNode(Graph parent, Lane lane) {
		this.parent = parent;
		this.lane = lane;
		listeners = new ArrayList<NodeListener>();
		
		parent.addNode(lane, this);
	}
	
	/**
	 * @see org.workhorse.graph.node.Node#addListener(org.workhorse.graph.node.NodeListener)
	 */
	public void addListener(NodeListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Returns the listeners to this node.
	 * @return  The node listeners
	 */
	protected Collection<NodeListener> getListeners() {
		return listeners;
	}
	
	/**
	 * Called when the node is entered.
	 * @param execution The execution entering the node
	 */
	protected void onEntering(Execution execution) {
		execution.enter(this);
		for(NodeListener listener : listeners) {
			listener.onEntering(this, execution);
		}
	}
	
	/**
	 * Called when node is about to be exited.
	 * @param execution The execution leaving the node
	 */
	protected void onLeaving(Execution execution) {
		for(NodeListener listener : listeners) {
			listener.onLeaving(this, execution);
		}
		execution.leave(this);
	}

	/**
	 * @see  org.workhorse.graph.node.Node#getLane()
	 */
	public Lane getLane() {
		return lane;
	}

	/**
	 * @see  org.workhorse.graph.node.Node#getParent()
	 */
	public Graph getParent() {
		return parent;
	}

	/**
	 * For handling any exceptions that occur during the execution
	 * of an activity.
	 * @param exec The current execution
	 * @param e The exception that was raised
	 */
	protected void handleUnexpected(Execution exec, Exception e) {
		logger.error("Unexpected runtime error", e);
		org.workhorse.event.Error error = 
			new org.workhorse.event.Error(this, "SYSTEM", e.getMessage());
		//TODO handle
		
	}
}
