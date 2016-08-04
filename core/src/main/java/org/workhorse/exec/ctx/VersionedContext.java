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

import org.workhorse.type.*;
import org.workhorse.type.val.Value;
import org.workhorse.type.var.Snapshot;
import org.workhorse.type.var.VersionedVariable;

/**
 * A shared {@link MutableContext} that uses {@link VersionedVariable}
 * to maintain consistent writes.
 *
 * @author Brennan Spies
 */
public interface VersionedContext extends MutableContext {

    /**
     * Returns a writable variable from this context.
     * @param type The variable type
     * @param name The variable name
     * @return The variable
     */
    @Override <V> VersionedVariable<V> getVariable(Class<V> type, String name);

    /**
     * Returns a writable variable from this context.
     * @param name The variable name
     * @return The variable
     */
    @Override VersionedVariable<?> getVariable(String name);

    /**
     * Creates a versioned variable in this context.
     * @param value The value to set
     * @return The created variable
     * @throws IllegalArgumentException If a symbol with the same name already exists
     */
    @Override <V> VersionedVariable<V> createWritable(Value<V> value);

    /**
     * Create or update the versioned variable with the given value.
     * @param value The value to set
     * @param callback The callback to get the snapshot
     * @return The created or updated variable
     * @throws InconsistentWriteException If the read version != current version
     */
    <V> VersionedVariable<V> createOrUpdateWritable(Value<V> value, RetrieveSnapshotCallback callback);


    /** Functional interface to get the snapshot with a variable name */
    @FunctionalInterface
    public static interface RetrieveSnapshotCallback {
        <T> Snapshot<T> retrieve(Class<T> type, String name);
    }
}
