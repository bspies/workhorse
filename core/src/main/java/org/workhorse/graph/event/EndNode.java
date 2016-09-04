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

import org.workhorse.graph.BaseNode;
import org.workhorse.graph.Diagram;
import org.workhorse.graph.Lane;

/**
 * A terminating node.
 *
 * @author Brennan Spies
 */
public class EndNode extends BaseNode implements EventNode {
    /**
     * Constructor takes parent diagram and lane to which the node
     * belongs.
     * @param id      The node id
     * @param diagram The parent diagram
     * @param lane    The lane
     */
    public EndNode(String id, Diagram diagram, Lane lane) {
        super(id, diagram, lane);
    }
}
