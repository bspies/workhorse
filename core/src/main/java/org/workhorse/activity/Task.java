/*
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

import java.time.LocalDateTime;

/**
 *  Tasks are activities in a process that require user interaction.
 *
 * @author Brennan Spies
 */
public interface Task extends AtomicActivity, Recordable
{	
	/**
	 * Returns the user responsible for performing the task, or null
	 * if not assigned.
	 * @return The responsible user
	 */
	User getPerformer();
	
	/**
	 * Returns the status of this task.
	 * @return The status
	 */
	Status getStatus();

	/**
	 * Returns the date that this task is actually started by
	 * the assigned user.
	 * @return The date of task start
     */
	LocalDateTime getStartDate();
}
