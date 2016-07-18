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
package org.workhorse.graph.exec;

import org.workhorse.exec.ctx.Context;
import org.workhorse.expr.Condition;
import org.workhorse.graph.*;
import org.workhorse.process.SubProcess;

import java.util.Collection;

/**
 * Represents a {@link org.workhorse.process.SubProcess} node.
 *
 * @author Brennan Spies
 */
public class SubProcessNode extends ActivityNode<SubProcess> implements Diagram {

    private GraphDelegate delegate;

    /**
     * Constructor takes parent diagram and lane to which the exec
     * belongs.
     *
     * @param id The node id
     * @param diagram The parent diagram
     * @param lane The lane
     */
    public SubProcessNode(String id, Diagram diagram, Lane lane) {
        super(id, diagram, lane);
        delegate = new GraphDelegate();
    }

    /**
     * Overrides super method to return false, since this
     * is a non-atomic activity
     * @return False
     */
    @Override public boolean isAtomic() {
        return false;
    }

    /** {@inheritDoc} */
    @Override public Class<SubProcess> getInstanceType() {
        return SubProcess.class;
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

    @Override
    public void addFlow(SequenceFlow flow) {

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
}
