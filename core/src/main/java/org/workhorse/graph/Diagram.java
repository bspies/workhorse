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

import java.util.Collection;

/**
 * Represents a BPMN diagram, a graph of workflow
 * elements
 *
 * @author Brennan Spies
 */
public interface Diagram {
    /**
     * Returns all nodes in the graph.
     * @return The nodes
     */
    Collection<Node> getNodes();

    /**
     * Returns the node that has the given
     * id. Throws an exception if no such node
     * exists in this diagram.
     * @param id The node id
     * @return The node
     * @throws IllegalArgumentException If no such node exists
     */
    Node getNodeById(String id);

    /**
     * Returns all starting nodes in the graph, explicit or implicit.
     * @return The starting nodes
     */
    Collection<Node> getStartingNodes();

    /**
     * Adds a node to the graph. This method will
     * return true if the node was added to the diagram
     * or false if node was already present.
     * @param node The node to add
     * @return True if node was added
     */
    boolean addNode(Node node);

    /**
     * Adds a flow to the diagram.
     * @param flow The flow to add
     */
    void addFlow(SequenceFlow flow);

    /**
     * Adds a flow between two nodes.
     * @param source The source node
     * @param target The target node
     */
    void addFlow(Node source, Node target);

    /**
     * Adds a condition flow between two nodes.
     * @param source The source node
     * @param target The target node
     * @param condition The condition to evaluate
     */
    void addFlow(Node source, Node target, Condition condition);

    /**
     * Returns all flows in this graph.
     * @return The flows
     */
    Collection<SequenceFlow> getFlows();

    /**
     * Returns all outgoing flows from the given node.
     * @param node The source node for the flows
     * @return The flows outgoing from the node
     */
    Collection<SequenceFlow> getOutgoingOf(Node node);

    /**
     * Returns all outgoing flows from the given node that
     * are enabled in this context, i.e. where any {@link org.workhorse.expr.Condition},
     * if present, evaluates to true.
     * @param node The source node
     * @param context The evaluation context
     * @return The enabled flows outgoing from the node
     */
    Collection<SequenceFlow> getOutgoingOf(Node node, Context context);

    /**
     * Returns all the incoming flows into a given node.
     * @param node The target node for the flows
     * @return The flows coming into the node
     */
    Collection<SequenceFlow> getIncomingOf(Node node);
}
