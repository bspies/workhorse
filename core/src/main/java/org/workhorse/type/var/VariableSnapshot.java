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

import org.workhorse.type.val.BaseValue;

/**
 * Represents a snapshot of a {@link VersionedVariable}.
 *
 * @author Brennan Spies
 */
public class VariableSnapshot<V> extends BaseValue<V> implements Snapshot<V> {

    private final long version;
    private final V value;

    /**
     * Creates a variable snapshot with variable fields and a version.
     * @param name The variable name
     * @param type The variable type
     * @param value The snapshot value
     * @param version The snapshot version
     */
    VariableSnapshot(String name, Class<V> type, V value, long version) {
        super(name, type);
        this.value = value;
        this.version = version;
    }

    /** The snapshot value */
    @Override public V getValue() {
        return value;
    }

    /** The snapshot version */
    @Override public long getVersion() { return version; }
}
