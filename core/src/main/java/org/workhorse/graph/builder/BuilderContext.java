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

import org.workhorse.graph.Diagram;
import org.workhorse.graph.Node;
import org.workhorse.id.IdGenerator;
import org.workhorse.id.Identifiable;

import java.util.Optional;

/**
 * Interface for a builder context object.
 *
 * @author Brennan Spies
 */
public interface BuilderContext {
    /**
     * Generates an id for the given {@link Identifiable} type.
     * @param type The identifiable type
     * @return The id
     */
    String generateIdFor(Class<? extends Identifiable<String>> type);

    /**
     * Returns the current parent diagram.
     * @return The diagram
     */
    Diagram getParent();

    /**
     * Sets the current parent diagram.
     * @param parent The current parent
     */
    void setParent(Diagram parent);

    /**
     * Pass-through method for building an object
     * from the context.
     * @param builder The contextual builder
     * @return The object instance
     */
    <T> T build(ContextualBuilder<T> builder);

    /**
     * Get a built object by the builder that built it.
     * @param builder The original builder
     * @return The (optional) object
     */
    <T> Optional<T> getBuiltObject(ContextualBuilder<T> builder);

    /**
     * Get a built object by id, if it has been built.
     * @param type The node type
     * @param id The id of the object
     * @return The (optional) identifiable object
     */
    <T extends Identifiable<String>> Optional<T> getBuiltObjectById(Class<T> type, String id);

    /**
     * Returns all built nodes.
     * @return the built nodes
     */
    Iterable<Node> getNodes();
}
