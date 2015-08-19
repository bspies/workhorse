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
package org.workhorse.actor;

import java.util.Collection;

import org.workhorse.Element;
import org.workhorse.activity.Task;
import org.workhorse.actor.role.Position;

/**
 * Represents a single individual who is a user of the system.
 *
 * @author Brennan Spies
 */
public class User extends Element implements Actor
{
    private String email, name;

    //internal use only
    public User() {}

    /**
     * Creates a user.
     * @param name The user name
     */
    public User(String name) {
        this.name = name;
    }

	/**
	 * Gets the user's email.
	 * @return The user's email
	 */
	public String getEmail() {
       return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

    @Override
    public String getName() {
        return name;
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
