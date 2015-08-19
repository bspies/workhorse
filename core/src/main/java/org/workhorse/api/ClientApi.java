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
package org.workhorse.api;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Brennan Spies
 * 
 * <p>Interface for a client to the process engine.</p>
 */
public interface ClientApi 
{
	/**
	 * Registers a task listener.
     * @param id The id of the task
	 * @param listener The listener to register
	 */
	public void registerListener(Serializable id, TaskListener listener);
	
	/**
	 * Returns the task list of the current client.
	 * @return The current client's task list
	 */
	public Collection<TaskApi> geTaskList();
	
	/**
	 * Returns a client to all running processes with the given name.
	 * @param name The process name
	 * @return The process clients
	 */
	public Collection<ProcessApi> getProcesses(String name);
	
	/**
	 * Returns a client to the process identified by the given id, or
	 * null if none exists. 
	 * @param id The id of the process
	 * @return The process client
	 */
	public ProcessApi getProcess(Serializable id);
	
	/**
	 * Returns a client to the task identified by the given id, or
	 * null if none exists.
	 * @param id The id of the task
	 * @return The task client
	 */
	public TaskApi getTask(Serializable id);
}
