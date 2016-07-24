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

import org.workhorse.exec.ctx.Context;
import org.workhorse.expr.Condition;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Delegate implementation of the {@link Diagram} interface for
 * handling graph functionality.
 */
public class GraphDelegate implements Diagram {

    private Map<String,Node> nodes;
    private Set<SequenceFlow> sequenceFlows;

    /**
     * Default constructor.
     */
    public GraphDelegate() {
        nodes = new HashMap<>();
        sequenceFlows = new HashSet<>();
    }

    /** {@inheritDoc} */
    @Override public Collection<Node> getNodes() {
        return nodes.values();
    }

    /** {@inheritDoc} */
    @Override public Node getNodeById(String id) {
        return nodes.get(id);
    }

    /** {@inheritDoc} */
    @Override public Collection<Node> getStartingNodes() {
        return nodes.values()
                .stream().filter(node -> getIncomingOf(node).isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /** {@inheritDoc} */
    @Override public boolean addNode(Node node) {
        if(nodes.containsKey(node.getId())) {
            return false;
        } else {
            nodes.put(node.getId(), node);
            return true;
        }
    }

    private boolean hasNode(Node node) {
        return nodes.containsKey(node.getId());
    }

    /** {@inheritDoc} */
    @Override public void addFlow(SequenceFlow flow) {
        if(!hasNode(flow.getSource())) {
            throw new IllegalArgumentException(
                    String.format("Source '%s' is not present in the diagram, cannot add flow", flow.getSource())
            );
        }
        addNode(flow.getTarget());
        sequenceFlows.add(flow);
    }

    /** {@inheritDoc} */
    @Override public void addFlow(Node source, Node target) {
        addFlow(new SequenceFlow(source, target));
    }

    /** {@inheritDoc} */
    @Override
    public void addFlow(Node source, Node target, Condition condition) {
        SequenceFlow flow = new SequenceFlow(source, target);
        flow.setCondition(condition);
        addFlow(flow);
    }

    /** {@inheritDoc} */
    @Override public Collection<SequenceFlow> getFlows() {
        return sequenceFlows;
    }

    /** {@inheritDoc} */
    @Override public Collection<SequenceFlow> getOutgoingOf(Node node) {
        return sequenceFlows
                .stream()
                .filter(sequenceFlow -> sequenceFlow.getSource().equals(node))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /** {@inheritDoc} */
    @Override
    public Collection<SequenceFlow> getOutgoingOf(Node node, Context context) {
        return sequenceFlows
                .stream()
                .filter(sequenceFlow -> sequenceFlow.getSource().equals(node) && isTraversable(sequenceFlow, context))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //is this sequence flow traversable in the given contxt?
    private boolean isTraversable(SequenceFlow flow, Context ctx) {
        return flow.getCondition()==null || flow.getCondition().evaluate(ctx);
    }

    /** {@inheritDoc} */
    @Override public Collection<SequenceFlow> getIncomingOf(Node node) {
        return sequenceFlows
                .stream()
                .filter(sequenceFlow -> sequenceFlow.getTarget().equals(node))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
