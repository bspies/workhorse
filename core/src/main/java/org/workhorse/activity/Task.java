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
package org.workhorse.activity;

import org.workhorse.Recordable;
import org.workhorse.actor.User;
import org.workhorse.exec.ctx.LocalContext;

import java.util.Date;

/**
 *  Tasks are activities in a process that require user interaction.
 *
 * @author Brennan Spies
 */
public interface Task extends Activity, Recordable
{	
	/**
	 * Returns the user responsible for performing the task, or null
	 * if not assigned.
	 * @return The responsible user
	 */
	public User getPerformer();
	
	/**
	 * Returns the date that work on the task was started (i.e., acquired
	 * by the responsible <code>User</code>).
	 * @return The task start date
	 */
	public Date getStartDate();
	
	/**
	 * Returns the status of this task.
	 * @return The status
	 */
	public Status getStatus();

    public LocalContext getContext();
}
