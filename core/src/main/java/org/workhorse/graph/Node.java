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
package org.workhorse.graph;

import org.workhorse.Describable;
import org.workhorse.id.Identifiable;
import org.workhorse.expr.Condition;

import java.util.Collection;

/**
 * Interface to represent a "place" in a graph where
 * an event, activity, or control function is performed.
 *
 * @author Brennan Spies
 */
public interface Node extends Identifiable<String>, Describable {

    /**
     * Returns the parent of this node.
     * @return The parent process or subprocess diagram
     */
    Diagram getParent();

    /**
     * Returns the swimlane to which this node belongs.
     * @return The swimlane
     */
    Lane getLane();

    /**
     * Adds a transition from this node to the target node.
     * @param targetNode The target node
     */
    void addFlow(Node targetNode);

    /**
     * Adds a transition from this node to the target node with a condition.
     * @param targetNode The target node
     * @param condition The condition
     */
    void addFlow(Node targetNode, Condition condition);

    /**
     * The outgoing transitions from this node.
     * @return The outgoing transitions
     */
    Collection<SequenceFlow> getOutgoing();

    /**
     * The incoming transitions to this node.
     * @return The incoming transitions
     */
    Collection<SequenceFlow> getIncoming();
}
