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

import org.workhorse.graph.ProcessDiagram;
import org.workhorse.process.Process;
import org.workhorse.type.val.Value;

/**
 * The WorkHorse runtime engine.
 *
 * @author Brennan Spies
 */
public interface Engine {

    /**
     * Creates a {@code Process} instance from the given
     * process diagram.
     * @param processDiagram The process diagram
     * @param initialValues The initial context values (if any)
     * @return The process instance
     */
    Process create(ProcessDiagram processDiagram, Value<?>... initialValues);
}
