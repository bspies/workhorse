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
package org.workhorse.factory.impl;

import org.workhorse.activity.Task;
import org.workhorse.activity.impl.HumanTask;
import org.workhorse.define.SubProcessDefinition;
import org.workhorse.define.TaskDefinition;
import org.workhorse.exec.ctx.ActivityContext;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.Execution;
import org.workhorse.factory.ActivityFactory;
import org.workhorse.graph.node.SubProcessNode;
import org.workhorse.graph.node.TaskNode;
import org.workhorse.process.SubProcess;
import org.workhorse.process.impl.StandardSubProcess;

/**
 * @author Brennan Spies
 * 
 * <p>The default implementation of {@link ActivityFactory}.</p>
 */
public class DefaultActivityFactory implements ActivityFactory
{
    /**
     * @see org.workhorse.factory.ActivityFactory#createSubProcess(org.workhorse.graph.node.SubProcessNode, ActivityContext)
     */
	public SubProcess createSubProcess(SubProcessNode node, ActivityContext context) {
        SubProcessDefinition def = node.getDefinition();
		return new StandardSubProcess(def.getName(),
			def.getActivityFactory(), context);
	}

	/**
	 * @see org.workhorse.factory.ActivityFactory#createTask(org.workhorse.graph.node.TaskNode, ActivityContext,org.workhorse.exec.Execution)
	 */
	public Task createTask(TaskNode node, ActivityContext context, Execution execution) {
        TaskDefinition def = node.getDefinition();
		HumanTask task = new HumanTask(def.getName(), execution, context);
		task.setDescription(def.getDescription());
		return task;
	}
}
