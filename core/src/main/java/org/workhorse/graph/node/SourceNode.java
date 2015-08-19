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

import org.workhorse.expr.Condition;
import org.workhorse.graph.SequenceFlow;

/**
 * @author Brennan Spies
 * 
 * <p>Node that can be the source of a {@link SequenceFlow}.</p>
 */
public interface SourceNode extends Node
{	
	/**
	 * Adds a transition from this node to the target node.
	 * @param targetNode The target node
	 */
	public void addTransition(TargetNode targetNode);
	
	/**
	 * Adds a default transition from this node to the target node.
	 * @param targetNode The target node
	 */
	public void addDefaultTransition(TargetNode targetNode);
	
	/**
	 * Adds a transition from this node to the target node with a condition.
	 * @param targetNode The target node
	 * @param condition The condition
	 */
	public void addTransition(TargetNode targetNode, Condition condition);
	
	/**
	 * The outgoing transitions from this node.
	 * @return The outgoing transitions
	 */
	public Collection<SequenceFlow> getOutgoing();
}
