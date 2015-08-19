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

import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.sequence.SequenceBuilder;
import org.workhorse.sequence.Step;
import org.workhorse.factory.ActionFactory;

/**
 * Node that contains an action to be performed.
 *
 * @author Brennan Spies
 */
public class ActionNode extends ActivityNode 
{
	private ActionFactory actionFactory;
	
	/**
     * Constructs an action node with parent elements and an action factory.
	 * @param parent The parent graph
	 * @param lane The lane to which the node belongs
     * @param factory The action factory
	 */
	public ActionNode(Graph parent, Lane lane, ActionFactory factory) {
		super(parent, lane);
        this.actionFactory = factory;
	}
	
	/**
	 * Returns the {@link ActionFactory} associated with this node.
	 * @return  The action to be performed
	 */
	public ActionFactory getActionFactory() {
		return actionFactory;
	}

	/**
	 * @see org.workhorse.graph.node.ActivityNode#addSteps(org.workhorse.sequence.SequenceBuilder)
	 */
	@Override protected void addSteps(SequenceBuilder sequenceBuilder) {
		sequenceBuilder.add(Phase.PROCESS, new ActionStep(this));
	}
	
	/*
	 * Sequence step for Action node.
	 */
	private static class ActionStep implements Step
	{
		private ActionNode actionNode;
		
		ActionStep(ActionNode actionNode) {
			this.actionNode = actionNode;
		}
		
		public boolean perform(Execution execution) {
			execution.process(actionNode);
			return false;
		}
	}
}
