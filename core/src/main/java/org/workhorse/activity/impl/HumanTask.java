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
package org.workhorse.activity.impl;

import org.workhorse.activity.Status;
import org.workhorse.activity.Task;
import org.workhorse.actor.User;
import org.workhorse.exec.Execution;
import org.workhorse.exec.ctx.ActivityContext;
import org.workhorse.exec.ctx.LocalContext;

import java.util.Date;

/**
 * Represents a human-executed activity.
 *
 * @author Brennan Spies
 */
public class HumanTask extends BaseActivity implements Task
{
	private Date creationDate, startDate, completionDate;
	private User performer;
	private Status status;
    private ActivityContext context;
	private long executionId;	//to be continued when task done

    //For JPA only
    public HumanTask() {}
	
	/**
	 * Default constructor takes the current context.
	 * to be used with the task.
     * @param name The task name
	 * @param execution The execution that created this task
     * @param context The task context
	 */
	public HumanTask(String name, Execution execution, ActivityContext context) {
		super(name);
		this.executionId = execution.getId();
        this.context = context;
		this.creationDate = new Date();
	}
	
	/**
	 * @see org.workhorse.activity.Task#getPerformer()
	 */
    @Override
	public User getPerformer() {
		return performer;
	}

    /**
     * Sets the task performer
     * @param performer The assigned user
     */
    public void setPerformer(User performer) {
        this.performer = performer;
    }
	
	/**
	 * @see org.workhorse.activity.Task#getCreationDate()
	 */
    @Override
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @see org.workhorse.activity.Task#getCompletionDate()
	 */
    @Override
	public Date getCompletionDate() {
		return completionDate;
	}

	/**
	 * @see org.workhorse.activity.Task#getStartDate()
	 */
    @Override
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 *
	 */
    @Override
	public LocalContext getContext() {
		return context;
	}

	/**
	 * @see org.workhorse.activity.Task#getStatus()
	 */
    @Override
	public Status getStatus() {
		return status;
	}

	//on the completion of a task
	//it will signal the execution to resume
	private void signal() {
        //lookup execution
        //Execution execution = ...;
		//execution.resume();
	}
}
