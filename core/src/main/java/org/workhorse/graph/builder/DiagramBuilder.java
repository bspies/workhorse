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
package org.workhorse.graph.builder;

import org.workhorse.util.Version;
import org.workhorse.expr.Condition;
import org.workhorse.graph.Diagram;
import org.workhorse.graph.WorkflowDiagram;
import org.workhorse.graph.Node;
import org.workhorse.graph.ProcessDiagram;
import org.workhorse.util.Builder;

/**
 * Builder for {@link ProcessDiagram} instances.
 *
 * @author Brennan Spies
 */
public class DiagramBuilder implements Builder<Diagram> {

    private ProcessDiagram diagram;

    public DiagramBuilder(String processName, Version version) {
        diagram = new WorkflowDiagram(processName, version);
    }

    /**
     * Adds a sequence flow to the diagram.
     * @param source The source node
     * @param target The target node
     * @return The current diagram builder
     */
    public DiagramBuilder withFlow(Node source, NodeBuilder<? extends Node> target) {
        diagram.addFlow(source, target.build());
        return this;
    }

    /**
     * Adds a conditional sequence flow to the diagram.
     * @param source The source node
     * @param target The target node
     * @param condition The condition
     * @return The current diagram builder
     */
    public DiagramBuilder withFlow(Node source, NodeBuilder<? extends Node> target, Condition condition){
        diagram.addFlow(source, target.build(), condition);
        return this;
    }

    /**
     * Builds the final {@code Diagram}.
     * @return The diagram
     */
    @Override public Diagram build() {
        return diagram;
    }
}
