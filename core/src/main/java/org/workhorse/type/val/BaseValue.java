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
 * Base implementation of {@link Value} and {@link Variable} types.
 *
 * @author Brennan Spies
 */
public abstract class BaseValue<V> implements Value<V> {

    private final String name;
    private final Class<V> type;

    protected BaseValue(String name, Class<V> type) {
        this.name = name;
        this.type = type;
    }

    /** {@inheritDoc} */
    @Override public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override public Class<V> getType() {
        return type;
    }
}
