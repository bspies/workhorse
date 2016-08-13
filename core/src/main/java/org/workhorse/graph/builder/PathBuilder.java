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
package org.workhorse.graph.builder;

import org.workhorse.graph.Lane;
import org.workhorse.graph.Node;
import org.workhorse.graph.builder.container.DiagramBuilder;
import org.workhorse.graph.builder.node.EventNodeBuilder;
import org.workhorse.graph.event.StartNode;

/**
 * Builder for creating an execution path
 * associated with a given {@link Lane}.
 *
 * @author Brennan Spies
 */
public class PathBuilder {

    private DiagramBuilder parent;
    private ContextualBuilder<Lane> lane;

    /**
     * Creates the path builder with the
     * swim lane that it will run in.
     * @param parent The parent builder
     * @param lane The swim lane
     */
    public PathBuilder(DiagramBuilder parent, Lane lane) {
        this(parent, ExistingObject.wrap(lane));
    }

    /**
     * Creates the path builder with the swim
     * lane that it will run in.
     * @param parent The parent builder
     * @param laneBuilder The swim lane builder
     */
    public PathBuilder(DiagramBuilder parent,
                       ContextualBuilder<Lane> laneBuilder) {
        this.parent = parent;
        this.lane = laneBuilder;
    }

    /**
     * Creates a node with a start event.
     * @param name The node name
     * @return The builder for the event node
     */
    public FlowBuilder<StartNode> withStart(String name) {
        EventNodeBuilder<StartNode> enb = new EventNodeBuilder<>(name);
        parent.addNode(enb);
        return new FlowBuilder<>(parent, enb, lane);
    }

    /**
     * Creates an implicit starting node.
     * @param startingNode The node builder
     * @return The builder
     */
    public <N extends Node> FlowBuilder<N> withNode(ContextualBuilder<N> startingNode) {
        parent.addNode(startingNode);
        return new FlowBuilder<>(parent, startingNode, lane);
    }
}
