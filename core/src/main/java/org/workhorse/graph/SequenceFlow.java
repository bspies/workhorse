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

import org.workhorse.expr.Condition;

/**
 * The diagream element (represented as an edge between two vertices) that
 * is responsible for transitioning the {@link org.workhorse.exec.Execution}
 * from one node to the next.
 *
 * @author Brennan Spies
 */
public class SequenceFlow {

    private final Node source;
    private final Node target;
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

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public boolean hasCondition() {
        return condition != null;
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }
}
