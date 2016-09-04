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
import org.workhorse.graph.Node;
import org.workhorse.graph.builder.container.DiagramBuilder;
import org.workhorse.graph.builder.node.NodeReference;
import org.workhorse.util.MaybeType;

import java.util.function.Consumer;

/**
 * Intermediate builder to build the process flow.
 *
 * @author Brennan Spies
 */
public class FlowBuilder<T extends Node> {

    private DiagramBuilder parent;
    private ContextualBuilder<T> currentNode;
    private LaneReference currentLane;

    FlowBuilder(DiagramBuilder parent,
                ContextualBuilder<T> currentNode,
                LaneReference currentLane) {
        this.parent = parent;
        this.currentNode = currentNode;
        this.currentLane = currentLane;
    }

    /**
     * Creates a sequence flow to a referenced node.
     * @param id The id of the node
     * @return The current builder
     */
    public FlowBuilder<T> withFlow(String id) {
        parent.addFlow(edge(currentNode, id));
        return this;
    }

    /**
     * Creates a conditional sequence flow to a referenced node.
     * @param id The id of the node
     * @param condition The condition
     * @return The current builder
     */
    public FlowBuilder<T> withFlow(String id, Condition condition) {
        SequenceFlowBuilder flow = edge(currentNode, id);
        flow.setCondition(condition);
        parent.addFlow(flow);
        return this;
    }

    /**
     * Adds a group of divergent paths that merge on a given node.
     * @param joinNode The buiider of the merge node
     * @param paths The forked paths
     * @return The flow builder (from the merge node)
     */
    @SafeVarargs
    final public <N extends Node> FlowBuilder<N> withForkAndJoin(ContextualBuilder<N> joinNode,
                                                                 Consumer<ForkPathBuilder>... paths) {
        setLaneIfAbsent(joinNode);
        parent.addNode(joinNode);
        for(Consumer<ForkPathBuilder> cfp : paths) {
            ForkPathBuilder pathBuilder = new ForkPathBuilder(parent, currentLane, currentNode);
            cfp.accept(pathBuilder);
        }
        return new FlowBuilder<>(parent, joinNode, currentLane);
    }

    /**
     * Creates a forked execution path from the current node, in the current
     * lane.
     * @param forkedPath The lambda function with path builder
     * @return The current flow builder
     */
    public FlowBuilder<T> withFork(Consumer<ForkPathBuilder> forkedPath) {
        return withFork(currentLane, forkedPath);
    }

    /**
     * Creates a forked execution path from the current node in the given lane.
     * @param lane The lane for the execution path
     * @param forkedPath The lambda function with path builder
     * @return The current flow builder
     */
    public FlowBuilder<T> withFork(LaneReference lane, Consumer<ForkPathBuilder> forkedPath) {
        ForkPathBuilder pathBuilder = new ForkPathBuilder(parent, lane, currentNode);
        forkedPath.accept(pathBuilder);
        return this;
    }

    /**
     * Merges (and ends) the flow to the referenced node.
     * @param id The id of the node to merge to
     */
    public void mergeTo(String id) {
        SequenceFlowBuilder flow = edge(currentNode, id);
        parent.addFlow(flow);
    }

    /**
     * Creates a new node in the diagram as well as a sequence
     * flow to that node. Returns a flow builder with the newly created
     * node builder as its current reference.
     * @param nodeBuilder The node builder
     * @return The flow builder (from the new node)
     */
    public <N extends Node> FlowBuilder<N> withFlow(ContextualBuilder<N> nodeBuilder) {
        setLaneIfAbsent(nodeBuilder);
        parent.addNode(nodeBuilder);
        NodeReference<T> source = getNodeByBuilder(currentNode);
        NodeReference<N> target = getNodeByBuilder(nodeBuilder);
        parent.addFlow(new SequenceFlowBuilder(source, target));
        return new FlowBuilder<>(parent, nodeBuilder, currentLane);
    }

    /**
     * Creates a new node in the diagram as well as a conditional
     * sequence flow to that node. Returns a flow builder with the newly created
     * node builder as its current reference.
     * @param nodeBuilder The node builder
     * @param condition The flow condition
     * @return The flow builder (from the new node)
     */
    public <N extends Node> FlowBuilder<N> withFlow(ContextualBuilder<N> nodeBuilder, Condition condition) {
        setLaneIfAbsent(nodeBuilder);
        parent.addNode(nodeBuilder);
        NodeReference<T> source = getNodeByBuilder(currentNode);
        NodeReference<N> target = getNodeByBuilder(nodeBuilder);
        parent.addFlow(new SequenceFlowBuilder(source, target, condition));
        return new FlowBuilder<>(parent, nodeBuilder, currentLane);
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Helper methods
    //////////////////////////////////////////////////////////////////////////////////

    private void setLaneIfAbsent(ContextualBuilder<? extends Node> nodeBuilder) {
        MaybeType.of(LaneElementBuilder.class, nodeBuilder)
                .ifInstanceOf(leb -> leb.onLaneIfAbsent(currentLane));
    }

    /**
     * Creates a builder edge between the node builder and a reference to the node
     * with the given id.
     */
    private SequenceFlowBuilder edge(ContextualBuilder<? extends Node> src, String id) {
        return new SequenceFlowBuilder(getNodeByBuilder(src), getNodeById(id));
    }

    /** Creates a builder edge between the two node builders */
    private SequenceFlowBuilder edge(ContextualBuilder<? extends Node> src,
                                     ContextualBuilder<? extends Node> tgt) {
        return new SequenceFlowBuilder(getNodeByBuilder(src), getNodeByBuilder(tgt));
    }

    /** Returns a reference to the node with the given id. */
    private NodeReference<Node> getNodeById(String id) {
        return new NodeReference<>(context -> context.getBuiltObjectById(Node.class, id)
                .orElseThrow(() -> new IllegalStateException("Unable to find node with id: " + id)));
    }

    /** Returns a reference to the node represented by the given builder */
    private <N extends Node> NodeReference<N> getNodeByBuilder(ContextualBuilder<N> builder) {
        return new NodeReference<>(context -> context.getBuiltObject(builder)
                .orElseThrow(() -> new RuntimeException("Unable to create node reference to un-built object")));
    }
}
