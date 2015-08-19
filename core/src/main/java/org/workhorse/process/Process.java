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
package org.workhorse.process;

import java.util.Collection;

import org.workhorse.Recordable;
import org.workhorse.Startable;
import org.workhorse.exec.Execution;
import org.workhorse.graph.node.Node;
import org.workhorse.service.Service;


/**
 * Represents an instance of a process, or top-level executable.
 *
 * @author Brennan Spies
 */
public interface Process extends Container, Startable, Recordable
{	
    /**
     * Retrieves the service instance for the given service type
     * @param type The service type
     * @return The service instance
     */
    public <S extends Service> S getService(Class<S> type);
	
	/**
	 * Returns all executions belonging to this process instance.
	 * @return The executions
	 */
	public Collection<Execution> getExecutions();
	
	/**
	 * Returns all the executions which are paused at a particular
	 * node.
	 * @param node The current node for the paused execution
	 * @return The paused executions
	 */
	public Collection<Execution> getPausedExecutions(Node node);

    /**
     * Finds the node with the given id.
     * @param nodeId The id of the node
     * @return The node
     */
    public Node getNode(String nodeId);
}
