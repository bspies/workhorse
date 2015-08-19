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

import org.workhorse.Identifiable;
import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;

/**
 * Interface to represent a "place" in a graph where
 * an activity or control function is performed.
 *
 * @author Brennan Spies
 */
public interface Node extends Identifiable<String>
{	
	/**
	 * Adds a listener to this node.
	 * @param listener The listener to add
	 */
	public void addListener(NodeListener listener);
	
	/**
	 * Returns the parent of this node.
	 * @return The parent process or subprocess
	 */
	public Graph getParent();
	
	/**
	 * Returns the swimlane to which this node belongs.
	 * @return The swimlane
	 */
	public Lane getLane();
	
	/**
	 * Resumes the flow for the given execution, starting with the
	 * given phase.
	 * @param execution The execution to resume
	 * @param phase The phase in which to resume
	 */
	public void reenter(Execution execution, Phase phase);
}
