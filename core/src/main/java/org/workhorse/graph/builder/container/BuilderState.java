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

import org.workhorse.graph.Node;
import org.workhorse.graph.Pool;
import org.workhorse.graph.SequenceFlow;
import org.workhorse.graph.builder.ContextualBuilder;
import org.workhorse.id.Identifiable;

import java.util.*;

/**
 * Used to accumulate builders as well as track ongoing state
 * during the build itself.
 *
 * @author Brennan Spies
 */
class BuilderState {

    //the initial state
    private Set<ContextualBuilder<Pool>> pools = new HashSet<>();
    private Set<ContextualBuilder<? extends Node>> nodes = new HashSet<>();
    private Set<ContextualBuilder<SequenceFlow>> flows = new HashSet<>();
    //state during build
    private Map<ContextualBuilder<?>,Object> builtObjects = new HashMap<>();
    private Map<String,Object> builtObjById = new HashMap<>();

    //methods to create initial state
    void addPool(ContextualBuilder<Pool> poolBuilder) { pools.add(poolBuilder); }
    void addNode(ContextualBuilder<? extends Node> nodeBuilder) { nodes.add(nodeBuilder); }
    void addFlow(ContextualBuilder<SequenceFlow> flowBuilder) { flows.add(flowBuilder); }

    Set<ContextualBuilder<Pool>> getPools() { return pools; }
    Set<ContextualBuilder<? extends Node>> getNodes() { return nodes; }
    Set<ContextualBuilder<SequenceFlow>> getFlows() { return  flows; }

    /**
     * Registers a built object and its builder for later reference, e.g. from
     * a {@link org.workhorse.graph.builder.node.NodeReference}.
     * @param builder The builder
     * @param builtObj The built object from the builder
     */
    <T> void registerBuiltObject(ContextualBuilder<T> builder, T builtObj) {
        builtObjects.put(builder, builtObj);
        if(builtObj instanceof Identifiable) {
            String id = ((Identifiable)builtObj).getId().toString();
            builtObjById.put(id, builtObj);
        }
    }

    /**
     * Returns the (optional) built object given its type and id. Built object
     * must be an {@link Identifiable} type.
     * @param type The built object's type
     * @param id The id
     * @return The (optional) built object
     */
    <T extends Identifiable<String>> Optional<T> getBuiltObject(Class<T> type, String id) {
        Object value = builtObjById.get(id);
        if(value!=null) {
            if(!type.isAssignableFrom(value.getClass())) {
                throw new IllegalArgumentException(String.format("Type %s is not assignable from value type %s",
                        type.getName(), value.getClass().getName()));
            }
            return Optional.of(type.cast(value));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Returns an optional to a built object given the builder that
     * built it.
     * @param builder The object's builder
     * @return The optional value
     */
    <T> Optional<T> getBuiltObject(ContextualBuilder<T> builder) {
        Object value = builtObjects.get(builder);
        Class<T> type = builder.getBuiltType();
        if(value!=null) {
            if(!type.isAssignableFrom(value.getClass())) {
                throw new IllegalArgumentException(String.format("Type %s is not assignable from value type %s",
                        type.getName(), value.getClass().getName()));
            }
            return Optional.of(type.cast(value));
        } else {
            return Optional.empty();
        }
    }
}
