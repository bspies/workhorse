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

/**
 * @author Brennan Spies
 * 
 * <p>Listener for {@link Node} traversal events.</p>
 */
public interface NodeListener 
{
	/**
	 * Called when an <code>Execution</code> enters a node.
	 * @param node The node entered
	 * @param execution The execution entering the node
	 */
	public void onEntering(Node node, Execution execution);
	
	/**
	 * Called when an <code>Execution</code> leaves a node.
	 * @param node The node being exited
	 * @param execution The execution exiting the node
	 */
	public void onLeaving(Node node, Execution execution);
}
