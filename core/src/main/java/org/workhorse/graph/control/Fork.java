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

import org.workhorse.exec.Execution;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.graph.SequenceFlow;

/**
 * @author Brennan Spies
 * <p>A control node that splits execution into two or more paths unconditionally. Also known as Parallel Split or AND-Split.</p>
 */
public class Fork extends Gateway 
{
	public Fork(Graph parent, Lane lane) {
		super(parent, lane);
	}

	/**
	 * Does not consider any conditions on the outgoing transitions.
	 * @see org.workhorse.graph.node.IntermediateNode#getEnabledTransitions(org.workhorse.exec.Execution)
	 */
	@Override
	protected Collection<SequenceFlow> getEnabledTransitions(Execution execution) {
		return getOutgoing();
	}
}
