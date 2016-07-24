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

import java.util.Collection;

/**
 * Abstract base implementation of the {@link Node}
 * interface.
 *
 * @author Brennan Spies
 */
public abstract class BaseNode extends DiagramElement implements Node {

    private String name, description;
    private final Diagram parent;
    private final Lane lane;

    /**
     * Constructor takes parent diagram and lane to which the node
     * belongs.
     *
     * @param id The node id
     * @param diagram The parent diagram
     * @param lane The lane
     */
    public BaseNode(String id, Diagram diagram, Lane lane) {
        super(id);
        this.parent = diagram;
        this.lane = lane;
    }

    /** {@inheritDoc} */
    @Override public Diagram getParent() {
        return parent;
    }

    /** {@inheritDoc} */
    @Override public Lane getLane() {
        return lane;
    }

    /** {@inheritDoc} */
    @Override public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override public void setName(String name) {
       this.name = name;
    }

    /** {@inheritDoc} */
    @Override public String getDescription() {
        return description;
    }

    /** {@inheritDoc} */
    @Override public void setDescription(String description) {
        this.description = description;
    }

    /** {@inheritDoc} */
    @Override public void addFlow(Node targetNode) {
        getParent().addFlow(this, targetNode);
    }

    /** {@inheritDoc} */
    @Override public void addFlow(Node targetNode, Condition condition) {
        getParent().addFlow(this, targetNode, condition);
    }

    /** {@inheritDoc} */
    @Override public Collection<SequenceFlow> getOutgoing() {
        return getParent().getOutgoingOf(this);
    }

    /** {@inheritDoc} */
    @Override public Collection<SequenceFlow> getIncoming() {
        return getParent().getIncomingOf(this);
    }
}
