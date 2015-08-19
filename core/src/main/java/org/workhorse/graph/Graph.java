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
package org.workhorse.graph;

import java.util.Collection;

import org.workhorse.graph.node.Node;
import org.workhorse.graph.node.SourceNode;
import org.workhorse.graph.node.StartingNode;
import org.workhorse.graph.node.TargetNode;
import org.workhorse.flow.Controller;

/**
 * @author Brennan Spies
 * 
 * <p>A container for a collection of nodes.</p>
 */
public interface Graph 
{
	/**
	 * Returns all nodes in the graph.
	 * @return The nodes
	 */
	public Collection<Node> getNodes();
	
	/**
	 * Returns all starting nodes in the graph, explicit or implicit.
	 * @return The starting nodes
	 */
	public Collection<StartingNode> getStartingNodes();
	
	/**
	 * Adds a node to the graph.
	 * @param lane The lane to which the node will belong
	 * @param node The node to add
	 */
	public void addNode(Lane lane, Node node);
	
	/**
	 * Adds a transition between two nodes.
	 * @param sequenceFlow The transition to add
	 */
	public void addTransition(SequenceFlow sequenceFlow);
	
	/**
	 * Adds a transition between two nodes.
	 * @param source The source node
	 * @param target The target node
	 */
	public void addTransition(SourceNode source, TargetNode target);

    /**
     * Returns the {@link Controller} for this graph.
     * @return The controller
     */
    public Controller getController();
	
	/**
	 * Returns all transitions in this graph.
	 * @return The transitions
	 */
	public Collection<SequenceFlow> getTransitions();
	
	/**
	 * Returns all outgoing transitions from the given node.
	 * @param node The source node for the transitions
	 * @return The transitions outgoing from the node
	 */
	public Collection<SequenceFlow> getOutgoingOf(Node node);
	
	/**
	 * Returns all the incoming transitions into a given node.
	 * @param node The target node for the transitions
	 * @return The transitions coming into the node
	 */
	public Collection<SequenceFlow> getIncomingOf(Node node);
}
