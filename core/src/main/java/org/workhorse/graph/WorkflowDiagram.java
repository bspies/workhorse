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

import org.workhorse.util.Version;
import org.workhorse.exec.ctx.Context;
import org.workhorse.expr.Condition;

import java.util.Collection;
import java.util.HashSet;

/**
 * Default implementation of {@link Diagram} for the top-level process
 * diagram.
 *
 * @author Brennan Spies
 */
public class WorkflowDiagram implements ProcessDiagram {

    private String name, description;
    private GraphDelegate delegate;
    private Collection<Pool> pools;
    private Version version;

    /**
     * Default constructor.
     *
     * @param name The process name
     * @param version The diagram version
     */
    public WorkflowDiagram(String name, Version version) {
       this.name = name;
       this.version = version;
       pools = new HashSet<>();
       delegate = new GraphDelegate();
    }

    /** {@inheritDoc} */
    @Override public Version getVersion() {
        return version;
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
    @Override public Collection<Node> getNodes() {
        return delegate.getNodes();
    }

    /** {@inheritDoc} */
    @Override public Node getNodeById(String id) {
        return delegate.getNodeById(id);
    }

    /** {@inheritDoc} */
    @Override public Collection<Node> getStartingNodes() {
        return delegate.getStartingNodes();
    }

    /** {@inheritDoc} */
    @Override public boolean addNode(Node node) {
        return delegate.addNode(node);
    }

    /** {@inheritDoc} */
    @Override public void addFlow(SequenceFlow flow) {
        delegate.addFlow(flow);
    }

    /** {@inheritDoc} */
    @Override public void addFlow(Node source, Node target) {
        delegate.addFlow(source, target);
    }

    /** {@inheritDoc} */
    @Override public void addFlow(Node source, Node target, Condition condition) {
        delegate.addFlow(source, target, condition);
    }

    /** {@inheritDoc} */
    @Override public Collection<SequenceFlow> getFlows() {
        return delegate.getFlows();
    }

    /** {@inheritDoc} */
    @Override public Collection<SequenceFlow> getOutgoingOf(Node node) {
        return delegate.getOutgoingOf(node);
    }

    /** {@inheritDoc} */
    @Override public Collection<SequenceFlow> getOutgoingOf(Node node, Context context) {
        return delegate.getOutgoingOf(node, context);
    }

    /** {@inheritDoc} */
    @Override public Collection<SequenceFlow> getIncomingOf(Node node) {
        return delegate.getIncomingOf(node);
    }

    /** {@inheritDoc} */
    @Override public Collection<Pool> getPools() {
        return pools;
    }
}
