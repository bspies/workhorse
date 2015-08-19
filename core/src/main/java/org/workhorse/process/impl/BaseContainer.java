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
package org.workhorse.process.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.workhorse.Element;
import org.workhorse.activity.Task;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.impl.BaseExecutable;
import org.workhorse.factory.ActivityFactory;
import org.workhorse.process.Container;
import org.workhorse.process.State;

/**
 * Abstract base class for {@code Executable} implementations.
 *
 * @author Brennan Spies
 */
public abstract class BaseContainer extends BaseExecutable implements Container
{
	private Collection<Task> tasks;
	private State state;
	private ActivityFactory activityFactory;
	
	/**
	 * For internal use only.
	 */
	public BaseContainer() {}
	
	/**
	 * Creates an {@code Executable} with an {@link ActivityFactory}.
	 * @param activityFactory The activity factory
     * @param context The execution context
	 */
	public BaseContainer(ActivityFactory activityFactory, Context context) {
        this.tasks = new ArrayList<Task>();
		this.activityFactory = activityFactory;
		state = State.READY;
	}

	/**
	 * {@inheritDoc}
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Sets the state of the {@code Executable}.
	 * @param state The new state
	 */
	protected void setState(State state) {
		this.state = state;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ActivityFactory getActivityFactory() {
		return activityFactory;
	}
	
	/**
	 * Sets the activity factory for this executable.
	 * @param activityFactory The activity factory
	 */
	public void setActivityFactory(ActivityFactory activityFactory) {
		this.activityFactory = activityFactory;
	}

	/**
	 * @see org.workhorse.process.Container#getActiveTasks()
	 */
	public Collection<Task> getActiveTasks() {
		return null; //TODO use persistence service
	}

	/**
	 * @see  org.workhorse.process.Container#getTasks()
	 */
	public Collection<Task> getTasks() {
		return tasks;
	}

	/**
	 * @see org.workhorse.process.Container#isActive()
	 */
	public boolean isActive() {
		return state == State.RUNNING;
	}

}
