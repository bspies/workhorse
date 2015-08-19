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

import org.workhorse.Cancellable;
import org.workhorse.Identifiable;
import org.workhorse.Stoppable;
import org.workhorse.activity.Task;
import org.workhorse.exec.Executable;
import org.workhorse.factory.ActivityFactory;

/**
 * Represents an instance of any executable container of activities.
 *
 * @author Brennan Spies
 */
public interface Container extends Identifiable<Long>, Executable, Stoppable, Cancellable
{
	/**
	 * Retrieves all tasks that are still active.
	 * @return The active tasks
	 */
	public Collection<Task> getActiveTasks();
	
	/**
	 * Retrieves all tasks.
	 * @return The tasks
	 */
	public Collection<Task> getTasks();
	
	/**
	 * Returns the factory for creating instances of different <code>Activity</code>
	 * types.
	 * @return The task factory
	 */
	public ActivityFactory getActivityFactory();
	
	/**
	 * Gets the state of the process.
	 * @return The process state
	 */
	public State getState();
	
	/**
	 * Returns true if the executable instance is active.
	 * @return True if active, false otherwise
	 */
	public boolean isActive();
}