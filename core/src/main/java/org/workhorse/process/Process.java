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
package org.workhorse.process;

import org.workhorse.Cancellable;
import org.workhorse.Recordable;
import org.workhorse.util.Version;

/**
 * The top-level workflow process.
 *
 * @author Brennan Spies
 */
public interface Process extends Container, Cancellable, Recordable {

    /**
     * Starts the process.
     */
    void start();

    /**
     * Finishes the {@code Process} instance
     * via "normal" termination.
     */
    void end();

    /**
     * The version of the {@link org.workhorse.graph.ProcessDiagram}
     * with which this process was created.
     * @return The process version
     */
    Version getVersion();

    /**
     * Gets the service for the given service type.
     * @param serviceType The service type
     * @return The service instance
     */
    <T> T getService(Class<T> serviceType);
}
