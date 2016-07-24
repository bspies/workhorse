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
package org.workhorse.flow;

import org.workhorse.exec.Execution;
import org.workhorse.exec.RunResult;
import org.workhorse.exec.RunState;
import org.workhorse.graph.Node;

/**
 * The {@code Controller} controls the flow of the
 * {@link Execution} through the process diagram.
 *
 * @author Brennan Spies
 */
public interface Controller {

    /**
     * Runs the flow of this execution through
     * the process {@link org.workhorse.graph.Diagram diagram}. This
     * method will return the resulting {@code RunState}, either
     * {@link RunState#PAUSED} or {@link RunState#TERMINATED}.
     * @param node The starting node
     * @param execution The execution to start
     * @return The resulting {@code RunState}
     */
    RunResult run(Node node, Execution execution);
}
