package org.workhorse.graph.builder;

import org.workhorse.graph.Lane;
import org.workhorse.graph.builder.container.DiagramBuilder;
import org.workhorse.graph.builder.node.StartNodeBuilder;
import org.workhorse.graph.event.StartNode;

/**
 * Specialization of {@link PathBuilder} that can
 * be used to .
 */
public class StartPathBuilder extends PathBuilder {

    /**
     * Creates the path builder with the
     * swim lane that it will run in.
     *
     * @param parent The parent builder
     * @param lane The swim lane
     */
    public StartPathBuilder(DiagramBuilder parent, LaneReference lane) {
        super(parent, lane);
    }

    /**
     * Creates the path builder with the swim
     * lane that it will run in.
     *
     * @param parent The parent builder
     * @param laneBuilder The swim lane builder
     */
    public StartPathBuilder(DiagramBuilder parent, ContextualBuilder<Lane> laneBuilder) {
        super(parent, laneBuilder);
    }

    /**
     * Creates a node with a start event.
     * @param name The node name
     * @return The flow builder
     */
    public FlowBuilder<StartNode> withStart(String name) {
        return withStart(new StartNodeBuilder(name));
    }

    /**
     * Creates a node with a start event.
     * @return The flow builder
     */
    public FlowBuilder<StartNode> withStart() {
        return withStart(new StartNodeBuilder());
    }

    /**
     * Creates a node with a start event.
     * @param builder The start node builder
     * @return The flow builder
     */
    public FlowBuilder<StartNode> withStart(StartNodeBuilder builder) {
        builder.onLane(getLaneReference());
        getParent().addNode(builder);
        return new FlowBuilder<>(getParent(), builder, getLaneReference());
    }
}
