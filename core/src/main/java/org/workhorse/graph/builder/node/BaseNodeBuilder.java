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
import org.workhorse.graph.builder.*;
import org.workhorse.validation.Required;

/**
 * Base implementation of a node builder.
 *
 * @author Brennan Spies
 */
public abstract class BaseNodeBuilder<T extends Node, B extends BaseNodeBuilder<T,B>>
        extends BaseIdentifiableBuilder<T,B> implements LaneElementBuilder<B> {

    @Required private String name;
    @Required private LaneReference laneReference;
    private String description;

    /**
     * Sets the name of the node.
     * @param name The name to set
     * @return The node builder
     */
    public B withName(String name) {
        this.name = name;
        return self();
    }

    /**
     * Sets the description of the node.
     * @param description The description to set
     * @return The node builder
     */
    public B withDescription(String description) {
        this.description = description;
        return self();
    }

    /** {@inheritDoc} */
    @Override public B onLane(LaneReference laneRef) {
        this.laneReference = laneRef;
        return self();
    }

    /** {@inheritDoc} */
    @Override public B onLaneIfAbsent(LaneReference laneRef) {
        if(this.laneReference == null) {
            this.laneReference = laneRef;
        }
        return self();
    }

    //protected accessors for subtype builders
    protected String getName() { return name; }
    protected String getDescription() { return  description; }
    protected Lane getLane(BuilderContext ctx) { return laneReference.getLane(ctx); }
    protected LaneReference getLaneReference() { return laneReference; }
}