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

import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.flow.Controller;
import org.workhorse.graph.node.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the graphical control-flow perspective of a process execution.
 *
 * @author Brennan Spies
 */
public class ProcessDiagram implements Graph, Serializable 
{
	private Collection<Node> nodes;
	private Collection<SequenceFlow> sequenceFlows;
	private Collection<Pool> pools;
	private transient Controller controller;
	
	/**
	 * Default constructor.
	 * @param controller The execution controller
	 */
	public ProcessDiagram(Controller controller) {
		this.controller = controller;
		this.nodes = new HashSet<Node>();
		this.sequenceFlows = new HashSet<SequenceFlow>();
		this.pools = new HashSet<Pool>();
	}
	
	/**
	 * @see org.workhorse.graph.Graph#addNode(Lane, org.workhorse.graph.node.Node)
	 */
	public void addNode(Lane lane, Node node) {
		nodes.add(node);
	}
	
	/**
	 * Adds a pool to the process diagram.
	 * @param pool The pool to add
	 */
	public void addPool(Pool pool) {
		pools.add(pool);
	}
	
	/**
	 * @see org.workhorse.graph.Graph#addTransition(org.workhorse.graph.SequenceFlow)
	 */
	public void addTransition(SequenceFlow sequenceFlow) {
		sequenceFlows.add(sequenceFlow);
	}

	/**
	 * @see org.workhorse.graph.Graph#addTransition(org.workhorse.graph.node.SourceNode, org.workhorse.graph.node.TargetNode)
	 */
	public void addTransition(SourceNode source, TargetNode target) {
		addTransition(new SequenceFlow(source, target));
	}
	
	/**
	 * Returns the controller for this process diagram.
	 * @return The controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * @see  org.workhorse.graph.Graph#getNodes()
	 */
	public Collection<Node> getNodes() {
		return nodes;
	}
	
	/**
	 * Returns the process diagram's pools.
	 * @return  The pools for this process diagram
	 */
	public Collection<Pool> getPools() {
		return pools;
	}
	
	/**
	 * @see org.workhorse.graph.Graph#getStartingNodes()
	 */
	public Collection<StartingNode> getStartingNodes() {
		Set<StartingNode> startingNodes = new HashSet<StartingNode>();
		for(final Node n : nodes) {
			if(n instanceof StartNode) {
				startingNodes.add((StartNode)n);
            } else if(getIncomingOf(n).size()==0) {	//implicit start nodes
				startingNodes.add(new StartingNode() {
					public void start(Execution execution) {
						n.reenter(execution, Phase.ENTER);
					}});
			}
		}
		return startingNodes;
	}

	/**
	 * @see org.workhorse.graph.Graph#getIncomingOf(org.workhorse.graph.node.Node)
	 */
	public Collection<SequenceFlow> getIncomingOf(Node node) {
		Collection<SequenceFlow> incoming = new ArrayList<SequenceFlow>();
		for(SequenceFlow sequenceFlow : sequenceFlows) {
			if(sequenceFlow.getTarget().equals(node))
				incoming.add(sequenceFlow);
		}
		return incoming;
	}

	/**
	 * @see org.workhorse.graph.Graph#getOutgoingOf(org.workhorse.graph.node.Node)
	 */
	public Collection<SequenceFlow> getOutgoingOf(Node node) {
		Collection<SequenceFlow> outgoing = new ArrayList<SequenceFlow>();
		for(SequenceFlow sequenceFlow : sequenceFlows) {
			if(sequenceFlow.getSource().equals(node))
				outgoing.add(sequenceFlow);
		}
		return outgoing;
	}

	/**
	 * @see org.workhorse.graph.Graph#getTransitions()
	 */
	public Collection<SequenceFlow> getTransitions() {
		return sequenceFlows;
	}
}
