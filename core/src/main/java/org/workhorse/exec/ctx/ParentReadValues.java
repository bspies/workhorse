/*
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

import java.util.HashMap;
import java.util.Map;

/**
 * Represents values read from the parent context, i.e.
 * {@link Snapshot snapshots}.
 */
public class ParentReadValues implements ReadValues {

    private Map<String,Snapshot<?>> snapshots;

    /**
     * Creates the {@code ParentReadValues} with the values (snapshots).
     * @param parentValues The snapshots
     */
    public ParentReadValues(Iterable<Snapshot<?>> parentValues) {
        snapshots = new HashMap<>();
        parentValues.forEach(snapshot -> snapshots.put(snapshot.getName(), snapshot));
    }

    /** {@inheritDoc} */
    @Override public Iterable<Snapshot<?>> getSnapshots() {
        return snapshots.values();
    }

    /** {@inheritDoc} */
    @Override public <V> Snapshot<V> getSnapshot(Class<V> type, String name) {
        @SuppressWarnings("unchecked")
        Snapshot<V> snapshot = (Snapshot<V>) getSnapshot(name);
        if(snapshot!=null && type.isAssignableFrom(snapshot.getType())) {
            throw new IllegalArgumentException(
                    String.format("Variable type %s is not assignable from %s", snapshot.getType(), type)
            );
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override public Snapshot<?> getSnapshot(String name) {
        return snapshots.get(name);
    }
}
