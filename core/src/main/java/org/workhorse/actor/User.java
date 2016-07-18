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
package org.workhorse.actor;

import org.workhorse.activity.Task;

/**
 * Represents a single individual who is a user of the system.
 *
 * @author Brennan Spies
 */
public class User extends BaseActor
{
    private String email;

	/**
	 * Default constructor.
	 *
	 * @param id   The user id
	 * @param name The name of the user
	 */
	public User(long id, String name) {
		super(id, name);
	}

	/**
	 * Gets the user's email.
	 * @return The user's email
	 */
	public String getEmail() {
       return email;
    }

	/**
	 * Set the user's email.
	 * @param email The email
     */
    public void setEmail(String email) {
       this.email = email;
    }
	
	/**
	 * Retrieves a collection of active tasks to which
	 * this user has been assigned.
	 * @return The task list
	 */
/*	public Collection<Task> getTaskList() {
        return null;
    }*/
	
	/**
	 * Completes the task.
	 * @param task The task to complete
	 */
	public void complete(Task task) {

    }
	
	/**
	 * Delegates the task to the given user.
	 * @param task The task to delegate
	 * @param toUser The user who will perform the task
	 */
	public void delegate(Task task, User toUser) {

    }
	
	/**
	 * Skips the task.
	 * @param task The task to skip
	 */
	public void skip(Task task) {

    }
}
