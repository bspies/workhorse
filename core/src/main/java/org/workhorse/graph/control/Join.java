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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.workhorse.SystemException;
import org.workhorse.exec.Execution;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.graph.SequenceFlow;

/**
 * @author Brennan Spies
 *
 * <p>A control node representing an AND-Join, or synchronous merge.</p>
 */
public class Join extends Gateway 
{
	private Set<Serializable> entered;
	
	public Join(Graph graph, Lane lane) {
		super(graph, lane);
		entered = new HashSet<Serializable>();
	}

	/**
	 * @see org.workhorse.graph.control.Gateway#enter(org.workhorse.graph.SequenceFlow, org.workhorse.exec.Execution)
	 */
	@Override protected boolean enter(SequenceFlow sequenceFlow, Execution execution) 
	{	
		synchronized(this) {
			if(entered.contains(sequenceFlow.getId()))
				throw new SystemException("Illegal join state: transition id=" + 
					sequenceFlow.getId() + " has been entered twice");
			entered.add(sequenceFlow.getId());
			if(incomingIds().equals(entered)) {
				getParent().getController().join(execution.getProcess().getPausedExecutions(this));
				reset();
				return true;
			} else {
				execution.pause();
				return false;
			}
		}
	}
	
	//resets the state of the join
	private void reset() {
		entered.clear();
	}
	
	//gets all the ids of the incoming transitions
	private Set<Serializable> incomingIds() {
		Set<Serializable> incomingIds = new HashSet<Serializable>();
		for(SequenceFlow t : getIncoming()) {
			incomingIds.add(t.getId());
		}
		return incomingIds;
	}
}
