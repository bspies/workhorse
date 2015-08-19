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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.workhorse.define.SubProcessDefinition;
import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.graph.SequenceFlow;
import org.workhorse.sequence.SequenceBuilder;
import org.workhorse.sequence.Step;
import org.workhorse.flow.Controller;

/**
 * @author  Brennan Spies  A node that represents a <code>Subprocess</code> in the workflow.
 */
public class SubProcessNode extends ActivityNode implements Graph 
{
	private SubProcessDefinition definition;
	private Collection<Node> nodes;
	private Collection<SequenceFlow> sequenceFlows;
	
	public SubProcessNode(Graph parent, Lane lane) {
		super(parent, lane);
		nodes = new HashSet<Node>();
		sequenceFlows = new HashSet<SequenceFlow>();
	}
	
	/**
	 * @see org.workhorse.graph.Graph#addNode(Lane, org.workhorse.graph.node.Node)
	 */
	public void addNode(Lane lane, Node node) {
		if(!lane.equals(this.getLane()))
			throw new IllegalArgumentException("Invalid lane for subprocess node");
		nodes.add(node);		
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
     * @see org.workhorse.graph.Graph#getController()
     */
    public Controller getController() {
        return this.getParent().getController();
    }
	
	/**
	 * Return the subprocess definition.
	 * @return  The subprocess definition
	 * @uml.property  name="definition"
	 */
	public SubProcessDefinition getDefinition() {
		return definition;
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
	 * @see  org.workhorse.graph.Graph#getNodes()
	 * @uml.property  name="nodes"
	 */
	public Collection<Node> getNodes() {
		return nodes;
	}
	
	/**
	 * @see org.workhorse.graph.Graph#getStartingNodes()
	 */
	public Collection<StartingNode> getStartingNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.workhorse.graph.Graph#getTransitions()
	 */
	public Collection<SequenceFlow> getTransitions() {
		return sequenceFlows;
	}
	
	@Override protected void addSteps(SequenceBuilder sequenceBuilder) {
		sequenceBuilder.add(Phase.PROCESS, new SubProcessStep(this))
			.add(Phase.POST_PROCESS, new PostProcessStep(this));
	}
	
	//Implements the main processing step
	private static class SubProcessStep implements Step 
	{	
		/**
		 * @uml.property  name="node"
		 * @uml.associationEnd  
		 */
		SubProcessNode node;
		
		public SubProcessStep(SubProcessNode node) {
			this.node = node;
		}
		
		public boolean perform(Execution execution) {
			execution.process(node);
			return !execution.isPaused();
		}
	}
}
