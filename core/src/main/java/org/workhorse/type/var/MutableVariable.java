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
package org.workhorse.type.var;

import org.workhorse.type.val.Value;

/**
 * A simple implementation of {@link Variable} that
 * mutates the value "in-place", i.e. over-writing any
 * existing value.
 *
 * @author Brennan Spies
 */
public class MutableVariable<V> extends BaseVariable<V> implements Variable<V> {

    private V value;

    /**
     * Creates a mutable variable.
     * @param name The variable name
     * @param type The variable type
     * @param value The initial value
     */
    public MutableVariable(String name, Class<V> type, V value) {
        super(name, type);
        this.value = value;
    }

    /**
     * Creates a mutable variable from a value.
     * @param value The value
     */
    public MutableVariable(Value<V> value) {
        this(value.getName(), value.getType(), value.getValue());
    }

    /** {@inheritDoc} */
    @Override public V getValue() {
        return value;
    }

    /** {@inheritDoc} */
    @Override public void setValue(V value) {
        validate(value);
        this.value = value;
    }
}
