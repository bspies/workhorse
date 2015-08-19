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
package org.workhorse.exec;

import org.workhorse.Identifiable;
import org.workhorse.exec.ctx.Context;
import org.workhorse.graph.node.*;
import org.workhorse.process.Process;
import org.workhorse.var.QualifiedName;

import javax.annotation.Nullable;

/**
 * Represents a given "thread of execution" in a workflow.
 *
 * @author Brennan Spies
 */
public interface Execution extends Identifiable<Long>, Cloneable
{
	/**
	 * Returns the current context.
	 * @return The current context
	 */
	public Context<QualifiedName> getExecutionContext();

	/**
	 * Returns the process that owns this execution.
	 * @return The execution's process
	 */
	public Process getProcess();
	
	/**
	 * Creates a child of the current execution.
	 * @return The child.
	 */
	public Execution createChild();
	
	/**
	 * Returns the parent execution, if any.
	 * @return The parent execution
	 */
	@Nullable public Execution getParent();

	/**
	 * Callback method for when an execution first reaches a node.
	 * @param node The node entered
	 */
	public void enter(Node node);

	/**
	 * Callback method for when an execution leaves a node.
	 * @param node The node being left
	 */
	public void leave(Node node);

	/**
	 * Ends the execution (or "consumes" the token).
	 */
	public void end();
	
	/**
	 * Pauses at current state of execution.
	 */
	public void pause();

	/**
	 * Signals a pause in the execution, to be
	 * continued at the given continuation.
     * @param node The node at which the execution is paused
     * @param phase The phase of execution at this node
     */
	public void pause(Node node, Phase phase);

	/**
	 * Returns true if execution paused.
	 * @return True if paused, false if not
	 */
	public boolean isPaused();

	/**
	 * Resumes the execution.
	 */
	public void resume();
	
	/**
	 * Performs post-processing on an activity node.
	 * @param activityNode The current activity node
	 */
	public void postProcess(ActivityNode activityNode);

	/**
	 * Processes an {@code Action} node.
	 * @param actionNode The action node
	 */
	public void process(ActionNode actionNode);

	/**
	 * Processes a {@code Task} node.
	 * @param taskNode The task node
	 */
	public void process(TaskNode taskNode);

	/**
	 * Processes a {@code SubProcess} node.
	 * @param processNode The subprocess node
	 */
	public void process(SubProcessNode processNode);

	/**
	 * Processes an {@code Event} node.
	 * @param baseEventNode The event node
	 */
	public void process(EventNode baseEventNode);
	
	/**
	 * Overrides the Object.clone() to make a public method.
	 * @see java.lang.Object#clone()
	 * @return The clone
	 * @throws CloneNotSupportedException If an the object does not support cloning
	 */
	public Object clone() throws CloneNotSupportedException;
}