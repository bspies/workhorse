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
import org.workhorse.util.MaybeType;

/**
 * Builder for creating an execution path associated with a
 * given {@link Lane}.
 *
 * @author Brennan Spies
 */
public abstract class PathBuilder {

    private DiagramBuilder parent;
    private LaneReference lane;

    /**
     * Creates the path builder with the
     * swim lane that it will run in.
     * @param parent The parent builder
     * @param lane The swim lane
     */
    protected PathBuilder(DiagramBuilder parent, LaneReference lane) {
        this.parent = parent;
        this.lane = lane;
    }

    /**
     * Creates the path builder with the swim
     * lane that it will run in.
     * @param parent The parent builder
     * @param laneBuilder The swim lane builder
     */
    protected PathBuilder(DiagramBuilder parent,
                          ContextualBuilder<Lane> laneBuilder) {
        this.parent = parent;
        this.lane = new LaneReference(context ->
                context.getBuiltObject(laneBuilder)
                        .orElseThrow(() -> new IllegalStateException("Cannot find lane by its lane builder")));
    }

    /**
     * Creates the first node in the path.
     * @param startingNode The node builder
     * @return The builder
     */
    public <N extends Node> FlowBuilder<N> withNode(ContextualBuilder<N> startingNode) {
        setLaneIfAbsent(startingNode);
        parent.addNode(startingNode);
        return new FlowBuilder<>(parent, startingNode, lane);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // Internal methods
    /////////////////////////////////////////////////////////////////////////////////////

    protected <N extends Node> void setLaneIfAbsent(ContextualBuilder<N> nodeBuilder) {
        MaybeType.of(LaneElementBuilder.class, nodeBuilder).ifInstanceOf(leb -> leb.onLaneIfAbsent(lane));
    }

    protected LaneReference getLaneReference() { return lane; }

    protected DiagramBuilder getParent() { return parent; }
}
