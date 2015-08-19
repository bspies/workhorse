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

import java.util.Collection;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.graph.SequenceFlow;

/**
 * @author Brennan Spies
 * 
 * <p>Abstract base class for all intermediate nodes, i.e. nodes that may have both
 * incoming and outgoing transitions.</p>
 */
public abstract class IntermediateNode extends BaseSourceNode implements TargetNode 
{	
	/**
	 * Constructor takes parent graph and lane to which the node
	 * belongs.
	 * @param parent The parent graph
	 * @param lane The lane
	 */
	public IntermediateNode(Graph parent, Lane lane) {
		super(parent, lane);
	}

	/**
	 * @see org.workhorse.graph.node.TargetNode#getIncoming()
	 */
	public Collection<SequenceFlow> getIncoming() {
		return getParent().getIncomingOf(this);
	}
}
