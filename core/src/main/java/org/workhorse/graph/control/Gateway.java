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

import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.graph.SequenceFlow;
import org.workhorse.graph.node.IntermediateNode;

/**
 * @author Brennan Spies
 *
 */
public abstract class Gateway extends IntermediateNode 
{
	public Gateway(Graph parent, Lane lane) {
		super(parent, lane);
	}
	
	/**
	 * Implements the default entry behavior, which is to simply fire
	 * the appropriate event method on the node listeners.
	 * @param sequenceFlow
	 * @param execution
	 * @return True if execution should continue
	 */
	protected boolean enter(SequenceFlow sequenceFlow, Execution execution) {
		onEntering(execution);
		return true;
	}

	/**
	 * @see org.workhorse.graph.node.TargetNode#perform(org.workhorse.graph.SequenceFlow, org.workhorse.exec.Execution)
	 */
	public void perform(SequenceFlow sequenceFlow, Execution execution) {
		if(!enter(sequenceFlow, execution))
			return;
		leave(execution);
	}

	/**
	 * @see org.workhorse.graph.node.Node#reenter(org.workhorse.exec.Execution, Phase)
	 */
	public void reenter(Execution execution, Phase phase) {
		switch(phase)	{
			case ENTER: 
				if(!enter(null, execution))
					return;
			case LEAVE:
				leave(execution);
				break;
			default:
				throw new IllegalArgumentException("Cannot re-enter a gateway in phase=" + phase);
		}
	}
}
