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
package org.workhorse.graph.event;

import org.workhorse.event.ThrownEvent;
import org.workhorse.event.handler.Catcher;
import org.workhorse.graph.BaseNode;
import org.workhorse.graph.Diagram;
import org.workhorse.graph.Lane;

/**
 * Node that contains a {@link Catcher} for catching thrown events.
 *
 * @author Brennan Spies
 */
public class CatchingNode<E extends ThrownEvent<E>> extends BaseNode
{
    private final Catcher<E> catcher;

    /**
     * Constructs a catching node with the graph parent, lane to which the node
     * belongs, and a catcher.
     *
     * @param id The node id
     * @param parent The graph parent
     * @param lane The lane to which the node belongs
     * @param catcher The catcher
     */
    public CatchingNode(String id, Diagram parent, Lane lane, Catcher<E> catcher) {
        super(id, parent, lane);
        this.catcher = catcher;
    }
}
