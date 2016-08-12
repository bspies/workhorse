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

/**
 * Interface to represent a container for other diagram
 * element builders. Used internally.
 *
 * @author Brennan Spies
 */
public interface DiagramBuilder {

    /** Accumulator method, for internal use only */
    void addFlow(ContextualBuilder<SequenceFlow> flowBuilder);

    /** Accumulator method, for internal use only */
    void addNode(ContextualBuilder<? extends Node> nodeBuilder);

    /** Accumulator method, for internal use only */
    void addPool(ContextualBuilder<Pool> poolBuilder);
}
