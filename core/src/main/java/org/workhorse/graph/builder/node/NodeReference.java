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
package org.workhorse.graph.builder.node;

import org.workhorse.graph.Node;
import org.workhorse.graph.builder.BuilderContext;

import java.util.function.Function;

/**
 * A reference to an existing or future-built node.
 *
 * @author Brennan Spies
 */
public class NodeReference<T extends Node> {

    private Function<BuilderContext,T> nodeProvider;

    public NodeReference(Function<BuilderContext,T> nodeProvider) {
        this.nodeProvider = nodeProvider;
    }

    /**
     * Fetches the node from the builder context.
     * @param ctx The builder context
     * @return The node
     */
    public Node getNode(BuilderContext ctx) {
        return nodeProvider.apply(ctx);
    }
}
