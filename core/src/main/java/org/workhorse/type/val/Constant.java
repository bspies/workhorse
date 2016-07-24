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

import org.workhorse.type.var.Variable;

/**
 * An immutable value.
 *
 * @author Brennan Spies
 */
public class Constant<V> extends BaseValue<V> implements Value<V> {

    private final V value;

    /**
     * Creates the constant.
     * @param name The constant name
     * @param type The value type
     * @param value The value
     */
    public Constant(String name, Class<V> type, V value) {
        super(name, type);
        this.value = value;
    }

    /**
     * Creates the constant from a variable.
     * @param variable The variable to make read-only
     */
    public Constant(Variable<V> variable) {
        this(variable.getName(), variable.getType(), variable.getValue());
    }

    /** {@inheritDoc} */
    @Override public V getValue() {
        return value;
    }
}
