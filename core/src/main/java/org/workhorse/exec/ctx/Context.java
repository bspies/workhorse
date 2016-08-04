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

/**
 * Represents the execution context.
 *
 * @author Brennan Spies
 */
public interface Context {
    /**
     * Returns a readable symbol from the context.
     * @param type The value type
     * @param name The value name
     * @return The value
     */
    <V> Value<V> getValue(Class<V> type, String name);

    /**
     * Returns a readable symbol from the context.
     * @param name The value name
     * @return The value
     */
    Value<?> getValue(String name);

    /**
     * Returns true if a symbol with
     * the given name exists in this context.
     * @param name The symbol name
     * @return True if exists, false otherwise
     */
    boolean hasSymbol(String name);

    /**
     * Returns all the symbol names in this
     * context.
     * @return The names
     */
    Iterable<String> getNames();
}
