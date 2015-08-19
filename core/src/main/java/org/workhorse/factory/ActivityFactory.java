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
package org.workhorse.factory;

import org.workhorse.activity.Task;
import org.workhorse.exec.ctx.ActivityContext;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.Execution;
import org.workhorse.process.SubProcess;
import org.workhorse.graph.node.TaskNode;
import org.workhorse.graph.node.SubProcessNode;

/**
 * @author Brennan Spies
 * 
 * <p>Factory for creating activity instances.</p>
 */
public interface ActivityFactory 
{
	/**
	 * Creates a new task.
	 * @param node The task node
     * @param context The task context
     * @param execution The execution that created the task
     * @return The task instance
	 */
	public Task createTask(TaskNode node, ActivityContext context, Execution execution);
	
	/**
	 * Creates a new sub-process.
	 * @param node The subprocess node
     * @param context The subprocess context  
     * @return The subprocess instance
	 */
	public SubProcess createSubProcess(SubProcessNode node, ActivityContext context);
}
