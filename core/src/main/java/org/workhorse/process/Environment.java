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
package org.workhorse.process;

import org.workhorse.dependency.DependencyManager;
import org.workhorse.type.val.Value;

/**
 * Represents the global process environment.
 */
public interface Environment {

    /**
     * Returns the dependency manager for this process.
     * @return The dependency manager
     */
    DependencyManager getDependencyManager();

    /**
     * Sets the initial values in the process environment.
     * @param values The initial values
     */
    void setInitialValues(Value<?>... values);

    /**
     * Returns the initial values to start the process.
     * @return The initial values
     */
    Iterable<Value<?>> getInitialValues();
}
