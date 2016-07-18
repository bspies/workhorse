/*
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
package org.workhorse.runtime;

import org.workhorse.dependency.DependencyManager;
import org.workhorse.graph.ProcessDiagram;
import org.workhorse.process.Process;
import org.workhorse.process.ProcessFactory;
import org.workhorse.type.val.Value;

import javax.inject.Inject;

/**
 * The standard implementation of the Workhorse runtime engine.
 */
public class WorkflowEngine implements Engine {

    private final DependencyManager dependencyMgr;

    @Inject
    public WorkflowEngine(DependencyManager dependencyMgr) {
        this.dependencyMgr = dependencyMgr;
    }

    @Override
    public Process create(ProcessDiagram processDiagram, Value<?>... initValues) {
        ProcessFactory factory = dependencyMgr.getInstance(ProcessFactory.class);
        return factory.createProcess(processDiagram, initValues);
    }
}
