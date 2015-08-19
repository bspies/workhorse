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

import java.util.Date;

import org.workhorse.Cancellable;
import org.workhorse.Identifiable;
import org.workhorse.Recordable;
import org.workhorse.activity.Status;

/**
 * @author Brennan Spies
 *
 * <p>Represents the client interface to a {@link org.workhorse.activity.Task Task}</p>
 */
public interface TaskApi extends Identifiable, Cancellable, Recordable
{
	/**
	 * Returns the user information for the performer of the task.
	 * @return the performer's user information, or null if no performer designated
	 */
	public UserInfo getPerformer();
	
	/**
	 * Returns the date that work on the task was started (i.e., acquired
	 * by the responsible {@code User}).
	 * @return The task start date
	 */
	public Date getStartDate();
	
	/**
	 * Returns the status of the task.
	 * @return The status
	 */
	public Status getStatus();
	
	/**
	 * Method to accept (acquire) the given
	 * task, if it is currently unassigned.
	 */
	public void accept();
	
	/**
	 * Completes the task.
	 */
	public void complete();
	
	/**
	 * Delegates the task to the given user.
	 * @param user The user to delegate the task to
	 */
	public void delegate(UserInfo user);
	
	/**
	 * Skips the task.
	 */
	public void skip();
}
