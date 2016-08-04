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
package org.workhorse.exec.ctx;

import org.workhorse.type.val.Value;
import org.workhorse.type.var.Variable;

/**
 * A context that contains {@link Variable}
 * symbols.
 */
public interface MutableContext extends Context {
    /**
     * Get a {@code Variable} of the given type, with the given name.
     * @param type The variable type
     * @param name The variable name
     * @return The variable
     */
    <V> Variable<V> getVariable(Class<V> type, String name);

    /**
     * Get a {@code Variable} with the given name.
     * @param name The variable name
     * @return The variable
     */
    Variable<?> getVariable(String name);

    /**
     * Returns true if a {@code Variable} with the given symbol name
     * exists in this context. Will <i>not</i> return true if a {@code Value}
     * with the given name exists.
     * @param name The symbol name
     * @return True if a variable with this name exists
     */
    boolean hasWritable(String name);

    /**
     * Creates a variable in this context from the given value.
     * @param value The value to set
     * @return The variable
     * @throws IllegalArgumentException If symbol with the same name already exists
     */
    <V> Variable<V> createWritable(Value<V> value);
}
