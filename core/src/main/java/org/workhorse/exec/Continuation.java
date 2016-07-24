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
package org.workhorse.exec;

import org.workhorse.graph.Node;

import java.util.UUID;

/**
 * Captures the state of a paused {@link Execution}, so that it
 * can be resumed later.
 *
 * @author Brennan Spies
 */
public class Continuation {

    private UUID executionId;
    private String nodeId;

    /**
     * Creates a continuation from the given execution and the
     * node at which it is paused.
     * @param execution The execution
     * @param currentNode The current node
     */
    public Continuation(Execution execution, Node currentNode) {
        this.executionId = execution.getId();
        this.nodeId = currentNode.getId();
    }

    /**
     * The id of the execution
     * @return The id
     */
    public UUID getExecutionId() {
        return executionId;
    }

    /**
     * The id of the node at which the execution is paused.
     * @return The node id
     */
    public String getNodeId() {
        return nodeId;
    }
}
