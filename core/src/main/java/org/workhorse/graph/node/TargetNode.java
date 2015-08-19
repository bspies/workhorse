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

import org.workhorse.exec.Execution;
import org.workhorse.graph.SequenceFlow;

/**
 * @author Brennan Spies
 * <p>Marks a node that can be the target of a {@link SequenceFlow}.</p>
 */
public interface TargetNode extends Node
{
	/**
	 * The incoming transitions to this node.
	 * @return The incoming transitions
	 */
	public Collection<SequenceFlow> getIncoming();
	
	/**
	 * Performs the logic at this node.
	 * @param sequenceFlow The transition taken to this node
	 * @param execution The execution passed to this node
	 */
	public void perform(SequenceFlow sequenceFlow, Execution execution);
}
