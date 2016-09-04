package org.workhorse.graph.builder.node;

import org.workhorse.graph.builder.BuilderContext;
import org.workhorse.graph.event.StartNode;

/**
 * Builder for a start node.
 *
 * @author Brennan Spies
 */
public class StartNodeBuilder extends EventNodeBuilder<StartNode> {

    /**
     * Creates a start node builder with a name.
     * @param name The node name
     */
    public StartNodeBuilder(String name) {
        super(name);
    }

    /** Default constructor */
    public StartNodeBuilder() {}

    @Override
    public Class<StartNode> getBuiltType() {
        return StartNode.class;
    }

    @Override public StartNode build(BuilderContext ctx) {
        setIdIfAbsent(() -> ctx.generateIdFor(StartNode.class));
        runValidation();
        return new StartNode(getId(), ctx.getParent(), getLane(ctx));
    }
}
