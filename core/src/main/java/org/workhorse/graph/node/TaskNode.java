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
package org.workhorse.graph.node;

import org.workhorse.define.TaskDefinition;
import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.sequence.SequenceBuilder;
import org.workhorse.sequence.Step;

/**
 * @author Brennan Spies
 */
public class TaskNode extends ActivityNode 
{	
	private TaskDefinition taskDefinition;
	
	/**
     * Creates a {@code TaskNode} with references to the graph and
     * the swimlane in which it is defined, as well as the task
     * definition itself.
	 * @param parent The graph parent
	 * @param lane The swim lane
	 * @param definition The task definition
	 */
	public TaskNode(Graph parent, Lane lane, TaskDefinition definition) {
		super(parent, lane);
		this.taskDefinition = definition;
	}
	
	/**
	 * Returns the definition of the task at this node.
	 * @return The task definition at this node
	 */
	public TaskDefinition getDefinition() {
		return taskDefinition;
	}

	/**
	 * @see org.workhorse.graph.node.ActivityNode#addSteps(org.workhorse.sequence.SequenceBuilder)
	 */
	@Override protected void addSteps(SequenceBuilder sequenceBuilder) {
		sequenceBuilder.add(Phase.PROCESS, new TaskStep(this))
			   .add(Phase.POST_PROCESS, new PostProcessStep(this));
	}

	/*
	 * Task step.
	 */
	private static class TaskStep implements Step 
	{	
		TaskNode node;
		
		TaskStep(TaskNode node) {
			this.node = node;
		}
		
		public boolean perform(Execution execution) {
			execution.process(node);
			//wait for task completion, then resume in post-process
			execution.pause(node, Phase.POST_PROCESS);
			return false;
		}
	}
}
