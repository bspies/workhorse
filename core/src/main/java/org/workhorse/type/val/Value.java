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
package org.workhorse.type.val;

/**
 * Represents a symbol in the workflow system that has
 * a name and value.
 *
 * @author Brennan Spies
 */
public interface Value<V> {

    /**
     * Returns the symbol name of the value.
     * @return The name
     */
    String getName();

    /**
     * Returns the type of the value.
     * @return The value type
     */
    Class<V> getType();

    /**
     * Returns the underlying value.
     * @return The value
     */
    V getValue();
}
