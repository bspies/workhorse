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

import org.workhorse.graph.exec.ScriptNode;
import org.workhorse.script.ScriptResource;

/**
 * Implementation of {@link NodeBuilder} for building {@link ScriptNode}
 * objects.
 *
 * @author Brennan Spies
 */
public class ScriptBuilder implements NodeBuilder<ScriptNode> {

    private ScriptNode node;

    ScriptBuilder(ScriptResource resource) {

    }

    /** Builds the script node. */
    @Override public ScriptNode build() {
        return node;
    }
}