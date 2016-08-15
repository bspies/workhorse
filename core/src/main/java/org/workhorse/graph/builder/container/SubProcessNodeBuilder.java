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
package org.workhorse.graph.builder.container;

import org.workhorse.graph.Diagram;
import org.workhorse.graph.Node;
import org.workhorse.graph.Pool;
import org.workhorse.graph.SequenceFlow;
import org.workhorse.graph.builder.BuilderContext;
import org.workhorse.graph.builder.ContextualBuilder;
import org.workhorse.graph.builder.PathBuilder;
import org.workhorse.graph.builder.node.ActivityNodeBuilder;
import org.workhorse.graph.exec.SubProcessNode;
import org.workhorse.util.VoidFunction;

/**
 * Builder for {@link SubProcessNode} instances.
 *
 * @author Brennan Spies
 */
public class SubProcessNodeBuilder extends ActivityNodeBuilder<SubProcessNode,SubProcessNodeBuilder>
        implements DiagramBuilder {

    private BuilderState builderState = new BuilderState();

    public SubProcessNodeBuilder(String name) {
        withName(name);
    }

    /**
     * Creates a path within the subprocess.
     * @param pathFunction The path function
     * @return The current builder
     */
    public SubProcessNodeBuilder withPath(VoidFunction<PathBuilder> pathFunction) {
        pathFunction.apply(new PathBuilder(this, getLaneReference()));
        return this;
    }

    @Override
    public void addFlow(ContextualBuilder<SequenceFlow> flowBuilder) {
        builderState.addFlow(flowBuilder);
    }

    @Override
    public void addNode(ContextualBuilder<? extends Node> nodeBuilder) {
        builderState.addNode(nodeBuilder);
    }

    @Override
    public void addPool(ContextualBuilder<Pool> poolBuilder) {
        builderState.addPool(poolBuilder);
    }

    /** {@inheritDoc} */
    @Override
    public SubProcessNode build(BuilderContext ctx) {
        setIdIfAbsent(ctx);
        runValidation();
        Diagram parent = ctx.getParent();
        //build subprocess
        SubProcessNode subProcessNode = new SubProcessNode(getId(), parent, getLane(ctx));
        ctx.setParent(subProcessNode);
        builderState.getNodes().forEach(nodeBuilder -> subProcessNode.addNode(ctx.build(nodeBuilder)));
        builderState.getFlows().forEach(flowBuilder -> subProcessNode.addFlow(ctx.build(flowBuilder)));
        //reset parent
        ctx.setParent(parent);
        return subProcessNode;
    }

    /** {@inheritDoc} */
    @Override
    public Class<SubProcessNode> getBuiltType() {
        return SubProcessNode.class;
    }
}
