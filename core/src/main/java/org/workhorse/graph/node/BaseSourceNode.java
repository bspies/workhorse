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
import org.workhorse.exec.Execution;
import org.workhorse.expr.Condition;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.graph.SequenceFlow;

/**
 * @author Brennan Spies
 * 
 * <p>Abstract base class for nodes that have outgoing transitions.</p>
 */
public abstract class BaseSourceNode extends BaseNode implements SourceNode 
{
	/**
	 * Constructor takes parent graph and lane to which the node
	 * belongs.
	 * @param graph The parent graph
	 * @param lane The lane
	 */
	public BaseSourceNode(Graph graph, Lane lane) {
		super(graph, lane);
	}
	
	/**
	 * @see org.workhorse.graph.node.SourceNode#addTransition(TargetNode)
	 */
	public void addTransition(TargetNode targetNode) {
		getParent().addTransition(this, targetNode);
	}
	
	public void addDefaultTransition(TargetNode targetNode) {
		SequenceFlow sequenceFlow = new SequenceFlow(this, targetNode);
		sequenceFlow.setDefaultTransition(true);
		getParent().addTransition(sequenceFlow);
	}
	
	/**
	 * @see org.workhorse.graph.node.SourceNode#addTransition(org.workhorse.graph.node.TargetNode, org.workhorse.expr.Condition)
	 */
	public void addTransition(TargetNode targetNode, Condition condition) {
		SequenceFlow sequenceFlow = new SequenceFlow(this, targetNode);
		sequenceFlow.setCondition(condition);
		getParent().addTransition(sequenceFlow);
	}

	/**
	 * @see org.workhorse.graph.node.SourceNode#getOutgoing()
	 */
	public Collection<SequenceFlow> getOutgoing() {
		return getParent().getOutgoingOf(this);
	}
	
	/**
	 * Returns the collection of enabled outgoing transitions.
	 * @param execution The current execution
	 * @return The enabled transitions
	 */
	protected Collection<SequenceFlow> getEnabledTransitions(Execution execution) 
	{
		//Default behavior is the same as MultiChoice
		Collection<SequenceFlow> enabled = new ArrayList<SequenceFlow>();
		SequenceFlow defaultTransition = null;
		for(SequenceFlow sequenceFlow : getOutgoing()) {
			if(sequenceFlow.isDefaultTransition())
				defaultTransition = sequenceFlow;
			else if(sequenceFlow.getCondition()==null || sequenceFlow.getCondition().evaluate(execution.getExecutionContext()))
				enabled.add(sequenceFlow);
		}
		if(enabled.isEmpty() && defaultTransition!=null)
			enabled.add(defaultTransition);
		return enabled;
	}
	

	/**
	 * Implements standard behavior for an execution leaving a node.
	 * @param execution The execution leaving the node
	 */
	protected void leave(Execution execution) 
	{
		Collection<SequenceFlow> outgoing = getEnabledTransitions(execution);
		if(outgoing.size()==0) {
			execution.end();
			//TODO check for active executions, end process if not
		} else {
			getParent().getController().take(execution, outgoing);
		}
	}
}
