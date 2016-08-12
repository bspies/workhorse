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

import org.workhorse.expr.Condition;
import org.workhorse.graph.Connector;
import org.workhorse.graph.SequenceFlow;
import org.workhorse.graph.builder.node.NodeReference;

import javax.annotation.Nullable;

/**
 * Builder for creating a {@link SequenceFlow} in
 * the process diagram.
 *
 * @author Brennan Spies
 */
public class SequenceFlowBuilder implements Connector<NodeReference,NodeReference>, ContextualBuilder<SequenceFlow> {

    private final NodeReference<?> source, target;
    @Nullable private Condition condition;

    /**
     * Creates an unconditional sequence flow between the two referenced nodes.
     * @param source The reference to the source node
     * @param target The reference to the target node
     */
    public SequenceFlowBuilder(NodeReference<?> source, NodeReference<?> target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Creates a conditional sequence flow between the two referenced nodes.
     * @param source The reference to the source node
     * @param target The reference to the target node
     * @param condition The condition
     */
    public SequenceFlowBuilder(NodeReference<?> source, NodeReference<?> target, Condition condition) {
        this(source, target);
        this.condition = condition;
    }

    /**
     * Returns the source of the edge.
     * @return The source
     */
    @Override
    public NodeReference getSource() {
        return source;
    }

    /**
     * Returns the target of the edge.
     * @return The target
     */
    @Override
    public NodeReference getTarget() {
        return target;
    }

    /**
     * Sets the condition on the edge.
     * @param condition The condition
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    /**
     * Builds the sequence flow.
     * @param ctx The context
     * @return The sequence flow
     */
    @Override
    public SequenceFlow build(BuilderContext ctx) {
        SequenceFlow flow = new SequenceFlow(source.getNode(ctx), target.getNode(ctx));
        if(condition!=null) {
            flow.setCondition(condition);
        }
        return flow;
    }

    /** {@inheritDoc} */
    @Override
    public Class<SequenceFlow> getBuiltType() {
        return SequenceFlow.class;
    }
}
