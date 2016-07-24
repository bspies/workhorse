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
package org.workhorse.graph.exec;

import org.workhorse.activity.Script;
import org.workhorse.script.ScriptResource;
import org.workhorse.graph.Diagram;
import org.workhorse.graph.Lane;

/**
 * Represents a node that runs a {@link Script}.
 *
 * @author Brennan Spies
 */
public class ScriptNode extends ActivityNode<Script> {

    private ScriptResource scriptReference;

    /**
     * Constructor takes parent diagram and lane to which the exec
     * belongs.
     *
     * @param id The node id
     * @param diagram The parent diagram
     * @param lane The lane
     */
    public ScriptNode(String id, Diagram diagram, Lane lane, ScriptResource scriptReference) {
        super(id, diagram, lane);
        this.scriptReference = scriptReference;
    }

    /**
     * Returns a reference to the script to be executed.
     * @return The script reference
     */
    public ScriptResource getScriptReference() {
        return scriptReference;
    }

    /** {@inheritDoc} */
    @Override public Class<Script> getInstanceType() {
        return Script.class;
    }
}
