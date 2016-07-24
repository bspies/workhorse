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

import org.workhorse.type.var.Snapshot;

/**
 * For an {@link org.workhorse.activity.Activity}, {@code ReadValues}
 * represents the values that were read from the parent context at
 * the time that the input parameters were evaluated.
 */
public interface ReadValues {
    /**
     * Gets all the snapshots.
     * @return The snapshots
     */
    Iterable<Snapshot<?>> getSnapshots();

    /**
     * Get the snapshot by variable name and type.
     * @param type The variable type
     * @param name The variable name
     * @return The snapshot
     */
    <V> Snapshot<V> getSnapshot(Class<V> type, String name);

    /**
     * Get the snapshot by name.
     * @param name The variable name
     * @return The snapshot
     */
    Snapshot<?> getSnapshot(String name);
}
