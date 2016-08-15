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
package org.workhorse.graph.builder.node;

import org.workhorse.activity.assign.Allocation;
import org.workhorse.graph.builder.BuilderContext;
import org.workhorse.graph.exec.TaskNode;

/**
 * Builder for {@link TaskNode} objects.
 *
 * @author Brennan Spies
 */
public class TaskNodeBuilder extends ActivityNodeBuilder<TaskNode,TaskNodeBuilder> {

    private Allocation allocation;

    public TaskNodeBuilder(String name) {
        withName(name);
    }

    /**
     * Sets the task assignment allocation.
     * @param allocation The assignment allocation
     * @return The task builder
     */
    public TaskNodeBuilder withAllocation(Allocation allocation) {
       this.allocation = allocation;
       return self();
    }

    /** Builds the task node */
    @Override public TaskNode build(BuilderContext ctx) {
        setIdIfAbsent(ctx);
        runValidation();
        TaskNode taskNode = new TaskNode(getId(), ctx.getParent(), getLane(ctx));
        taskNode.setName(getName());
        taskNode.setDescription(getDescription());
        return taskNode;
    }

    /** {@inheritDoc} */
    @Override
    public Class<TaskNode> getBuiltType() {
        return TaskNode.class;
    }
}
