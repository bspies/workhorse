/*
 * Copyright (c) 2009-2016 Brennan Spies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.workhorse.exec;

import org.workhorse.process.Process;

/**
 * Factory interface for creating {@link Execution} instances.
 *
 * @author Brennan Spies
 */
public interface ExecutionFactory {

    /**
     * Creates the execution with a reference to
     * the parent process.
     * @param process The parent process
     * @return The execution
     */
    Execution create(Process process);
}
