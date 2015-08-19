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
import org.workhorse.graph.SequenceFlow;
import org.workhorse.graph.node.IntermediateNode;

/**
 * @author Brennan Spies
 * <p>Abstract base type for {@link org.workhorse.event.Event} triggering or handling intermediate nodes.</p>
 */
public abstract class BaseEventNode extends IntermediateNode implements EventNode
{	
	public BaseEventNode(Graph parent, Lane lane) {
		super(parent, lane);
	}
	
	/**
	 * @see org.workhorse.graph.node.TargetNode#perform(org.workhorse.graph.SequenceFlow, org.workhorse.exec.Execution)
	 */
	public void perform(SequenceFlow sequenceFlow, Execution execution) {
		reenter(execution, Phase.ENTER);
	}
	
	/**
	 * @see org.workhorse.graph.node.Node#reenter(org.workhorse.exec.Execution, Phase)
	 */
	public void reenter(Execution execution, Phase phase) {
		switch(phase) {
			case ENTER:
				onEntering(execution);
			case PROCESS:
				execution.process(this);
				if(execution.isPaused())
					break;
			case LEAVE:
				onLeaving(execution);
				leave(execution);
				break;
			default:
				throw new IllegalArgumentException("Invalid phase for re-entry into event node: " 
					+ phase);
		}
	}
}
