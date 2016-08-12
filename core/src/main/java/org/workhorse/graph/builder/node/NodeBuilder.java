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
package org.workhorse.graph.builder.node;

import org.workhorse.graph.Lane;
import org.workhorse.graph.Node;
import org.workhorse.graph.builder.ContextualBuilder;

/**
 * Builder interface for {@link Node} instances.
 *
 * @author Brennan Spies
 */
public interface NodeBuilder<T extends Node> extends ContextualBuilder<T> {

    /**
     * Sets the name of the node.
     * @param name The name to set
     * @return The current builder
     */
    NodeBuilder<T> withName(String name);

    /**
     * Sets the description of the node.
     * @param description The description to set
     * @return The current builder
     */
    NodeBuilder<T> withDescription(String description);

    /**
     * Sets the id of the node.
     * @param id The id to set
     * @return The current builder
     */
    NodeBuilder<T> withId(String id);

    /**
     * Sets the swim lane of the node.
     * @param lane The swim lane
     * @return The current builder
     */
    NodeBuilder<T> onLane(Lane lane);
}
