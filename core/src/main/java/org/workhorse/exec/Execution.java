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

import org.workhorse.id.Identifiable;
import org.workhorse.activity.Activity;
import org.workhorse.exec.ctx.Context;
import org.workhorse.graph.exec.ActivityNode;
import org.workhorse.graph.event.EventNode;
import org.workhorse.process.Process;

import java.util.UUID;

/**
 * Represents a given "thread of execution" in a workflow.
 *
 * @author Brennan Spies
 */
public interface Execution extends Identifiable<UUID> {

    /**
     * Returns the currently executing context
     * of this execution.
     * @return The current context
     */
    Context getCurrentContext();

    /**
     * Returns the process to which this execution belongs.
     * @return The process
     */
    Process getProcess();

    /**
     * Visits an activity node in the process {@link org.workhorse.graph.Diagram}.
     * @param node The node being visited
     * @return The current running state of the execution
     */
    <T extends Activity> RunState visit(ActivityNode<T> node);

    /**
     * Visits an event node in the process {@link org.workhorse.graph.Diagram}.
     * @param node The node being visited
     * @return The current running state of the execution
     */
    RunState visit(EventNode node);

    /**
     * Is this execution in a paused state?
     * @return True if execution paused
     */
    boolean isPaused();
}
