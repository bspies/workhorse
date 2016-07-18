/*
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

/**
 * The "result" of a given execution run.
 *
 * @author Brennan Spies
 */
public class RunResult {

    private RunState state;
    private Node currentNode;

    private RunResult(RunState state) { this.state = state; }
    private RunResult(RunState state, Node currentNode) {
        this(state);
        this.currentNode = currentNode;
    }

    /**
     * Returns the run state of the execution.
     * @return The run state
     */
    public RunState getState() { return  state; }

    /**
     * Retuns the current node at which the execution is paused,
     * but only if the state is {@link RunState#PAUSED}. Otherwise
     * this method will return {@code null}.
     * @return The current node, or null
     */
    public Node getCurrentNode() { return currentNode; }

    /**
     * Factory method to return a {@code RunResult} for a terminated execution.
     * @return The terminated run result
     */
    public static RunResult terminated() {
        return new RunResult(RunState.TERMINATED);
    }

    /**
     * Factory method to return a {@code RunResult} for a paused execution.
     * @param pausedNode The node at which the execution is paused
     * @return The paused run result
     */
    public static RunResult paused(Node pausedNode) {
        return new RunResult(RunState.PAUSED, pausedNode);
    }

    /**
     * Factory method to return a {@code RunResult} for an errored execution.
     * @param errorNode The node at which the error occurred
     * @return The errored run result
     */
    public static RunResult error(Node errorNode) {
        return new RunResult(RunState.ERROR, errorNode);
    }
}
