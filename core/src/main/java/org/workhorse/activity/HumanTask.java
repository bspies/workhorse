/*
 * Copyright (c) 2009-2016 Brennan Spies
 *
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

import org.workhorse.actor.User;
import org.workhorse.exec.Execution;
import org.workhorse.exec.RunState;
import org.workhorse.exec.ctx.MutableContext;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a human-executed activity.
 *
 * @author Brennan Spies
 */
public class HumanTask extends BaseAtomicActivity implements Task
{
	private LocalDateTime creationDate, startDate, completionDate;
	private User performer;
	private Status status;
	
	/**
	 * Default constructor takes the current context.
	 * to be used with the task.
	 * @param id The task id
     * @param name The task name
     * @param context The task context
	 */
	public HumanTask(UUID id, String name, MutableContext context) {
		super(id, name, context);
		this.creationDate = LocalDateTime.now();
	}
	
	/**
	 * Returns the peformer of this task.
     * @return The performer
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

	/** {@inheritDoc} */
    @Override public LocalDateTime getCreationDate() {
		return creationDate;
	}

    /** {@inheritDoc} */
    @Override public LocalDateTime getCompletionDate() {
		return completionDate;
	}

    /** {@inheritDoc} */
    @Override public LocalDateTime getStartDate() {
		return startDate;
	}

	/**
	 * Returns the task status.
     * @return The status
	 */
    @Override public Status getStatus() {
		return status;
	}

	//on the completion of a task
	//it will signal the execution to resume
	private void signal() {
        //lookup execution
        //Execution execution = ...;
		//execution.resume();
	}

    /** {@inheritDoc} */
	@Override public RunState perform(Execution execution) {
		//todo notify participant
        return RunState.PAUSED;
	}
}
