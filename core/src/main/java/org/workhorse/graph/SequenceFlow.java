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
package org.workhorse.graph;

import org.workhorse.expr.Condition;

import javax.annotation.Nullable;

/**
 * The diagram element (represented as an edge between two vertices) that
 * indicates a transition for the {@link org.workhorse.exec.Execution}
 * from one node to the next.
 *
 * @author Brennan Spies
 */
public class SequenceFlow implements Connector<Node,Node> {

    private final Node source, target;
    //optional condition
    private Condition condition;

    /**
     * Constructs a sequence flow between the two nodes.
     * @param source the source exec
     * @param target the target exec
     */
    public SequenceFlow(Node source, Node target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Sets a condition on the sequence flow.
     * @param condition The condition
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    /**
     * Gets the condition (if present) on the sequence flow.
     * @return The condition, or null
     */
    @Nullable
    public Condition getCondition() {
        return condition;
    }

    /**
     * Checks if condition is present.
     * @return True if there is a condition
     */
    public boolean hasCondition() {
        return condition != null;
    }

    /**
     * Gets the source of the sequence flow.
     * @return The source node
     */
    @Override public Node getSource() {
        return source;
    }

    /**
     * Gets the target of the sequence flow.
     * @return The target node
     */
    @Override public Node getTarget() {
        return target;
    }
}
