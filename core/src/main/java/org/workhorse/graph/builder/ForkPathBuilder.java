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

/**
 * A divergent path builder.
 *
 * @author Brennan Spies
 */
public class ForkPathBuilder extends PathBuilder {

    private ContextualBuilder<? extends Node> fromNode;

    /**
     * Creates the path builder with the
     * swim lane that it will run in.
     *
     * @param parent The parent builder
     * @param lane   The swim lane
     */
    public ForkPathBuilder(DiagramBuilder parent,
                           LaneReference lane,
                           ContextualBuilder<? extends Node> fromNode) {
        super(parent, lane);
        this.fromNode = fromNode;
    }

    /**
     * Creates the path builder with the swim
     * lane that it will run in.
     *
     * @param parent      The parent builder
     * @param laneBuilder The swim lane builder
     */
    public ForkPathBuilder(DiagramBuilder parent,
                           ContextualBuilder<Lane> laneBuilder,
                           ContextualBuilder<? extends Node> fromNode) {
        super(parent, laneBuilder);
        this.fromNode = fromNode;
    }
}
