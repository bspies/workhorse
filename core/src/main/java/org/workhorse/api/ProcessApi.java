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

import java.util.Collection;
import org.workhorse.Cancellable;
import org.workhorse.Identifiable;
import org.workhorse.Recordable;
import org.workhorse.Startable;
import org.workhorse.process.State;

/**
 * @author Brennan Spies
 *
 * <p>Represents the client interface to the {@link org.workhorse.process.Process Process}.</p>
 */
public interface ProcessApi extends Identifiable, Startable, Cancellable, Recordable
{
	/**
	 * Returns all tasks that are currently active in the process
	 * instance.
	 * @return The active tasks
	 */
	public Collection<TaskApi> getActiveTasks();
	
	/**
	 * Returns all tasks in the process instance.
	 * @return
	 */
	public Collection<TaskApi> getTasks();
	
	/**
	 * Gets the state of the process.
	 * @return The process state
	 */
	public State getState();
	
	/**
	 * Returns true if the process instance is active.
	 * @return True if active, false otherwise
	 */
	public boolean isActive();
}
