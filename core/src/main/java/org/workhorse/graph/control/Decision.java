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
package org.workhorse.graph.control;

import java.util.Collection;
import java.util.Collections;

import org.workhorse.exec.Execution;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.graph.SequenceFlow;

/**
 * A control node where zero or only one (exclusive gateway) of many outgoing transitions is chosen.
 *
 * @author Brennan Spies
 */
public class Decision extends Gateway 
{
	private SequenceFlow defaultTransition;
	
	public Decision(Graph graph, Lane lane) {
		super(graph, lane);
	}
	
	/**
	 * Constructs a decision with a default transition.
	 * @param graph The graph
     * @param lane The lane
     * @param defaultTransition The default transition
	 */
	public Decision(Graph graph, Lane lane, SequenceFlow defaultTransition) {
		super(graph, lane);
		this.defaultTransition = defaultTransition;
	}
	
	/**
	 * Sets the default transition, taken if no other transitions are enabled (i.e., their conditions do not evaluate to true).
	 * @param defaultTransition  The default transition
	 */
	public void setDefaultTransition(SequenceFlow defaultTransition) {
		this.defaultTransition = defaultTransition;
	}
	
	/**
	 * Returns the first transition with a true condition (null=true in this case) as encountered
	 * in the iteration order of the outgoing transitions. If none of the transition conditions
	 * evaluate to true, the default transition (if present) is returned.
	 * @see org.workhorse.graph.node.IntermediateNode#getEnabledTransitions(org.workhorse.exec.Execution)
	 */
	@Override
	protected Collection<SequenceFlow> getEnabledTransitions(Execution execution) {
		Collection<SequenceFlow> outgoing = getParent().getOutgoingOf(this);
		for(SequenceFlow sequenceFlow : outgoing) {
			if(sequenceFlow.equals(defaultTransition))
				continue;
			if(sequenceFlow.getCondition()==null || sequenceFlow.getCondition().evaluate(execution.getExecutionContext()))
				return Collections.singletonList(sequenceFlow);
		}
		//either default or none
		if(defaultTransition!=null)
			return Collections.singletonList(defaultTransition);
		else
			return Collections.emptyList();
	}
}
