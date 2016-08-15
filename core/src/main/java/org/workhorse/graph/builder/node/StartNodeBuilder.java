package org.workhorse.graph.builder.node;

import org.workhorse.graph.builder.BuilderContext;
import org.workhorse.graph.event.EventNode;
import org.workhorse.graph.event.StartNode;

/**
 * Builder for a start node.
 *
 * @author Brennan Spies
 */
public class StartNodeBuilder extends EventNodeBuilder<StartNode> {

    public StartNodeBuilder(String name) {
        super(name);
    }

    @Override
    public Class<StartNode> getBuiltType() {
        return StartNode.class;
    }

    @Override public StartNode build(BuilderContext ctx) {
        setIdIfAbsent(() -> ctx.generateIdFor(EventNode.class));
        runValidation();
        return new StartNode(getId(), ctx.getParent(), getLane(ctx));
    }
}
