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
package org.workhorse.type.var;

import java.util.concurrent.atomic.AtomicReference;

/**
 * A thread-safe, versioned implementation of {@link Variable}.
 */
public class ConsistentVariable<V> extends BaseVariable<V> implements VersionedVariable<V> {

    private final AtomicReference<VersionedValue<V>> versionedValue;

    /**
     * Creates a new versioned variable with an initial value.
     * @param name The variable name
     * @param type The variable type
     * @param initValue The initial value
     */
    public ConsistentVariable(String name, Class<V> type, V initValue) {
        this(name, type, initValue, 0);
    }

    /**
     * Creates a versioned variable with initial value and version.
     * @param name The variable name
     * @param type The variable type
     * @param initValue The initial value
     * @param initVersion The initial version
     */
    private ConsistentVariable(String name, Class<V> type, V initValue, long initVersion) {
        super(name, type);
        versionedValue = new AtomicReference<>(new VersionedValue<>(initVersion, initValue));
    }

    /**
     * Returns a snapshot of the {@code ConsistentVariable} so that it can be compared
     * later with the same variable to determine issues with consistent updates.
     * @return The snapshot
     * @see VersionedVariable#compareAndSet(Snapshot, Object)
     */
    @Override public VariableSnapshot<V> snapshot() {
        VersionedValue<V> current = versionedValue.get();
        return new VariableSnapshot<>(getName(), getType(), current.getValue(), current.getVersion());
    }

    /**
     * Compares the version with the current version, and sets the
     * value if they match.
     * @param version The version to compare
     * @param newValue The value to set
     * @return True if value set, false if version not matched
     */
    private boolean compareAndSet(long version, V newValue) {
        VersionedValue<V> result =
                versionedValue.updateAndGet(prev -> prev.getVersion()==version ?
                        new VersionedValue<>(prev.getVersion() + 1, newValue) : prev);
        return result.getVersion()!=version;
    }

    /**
     * Compares the snapshot with this {@code ConsistentVariable}
     * and sets the {@code newValue} if they match.
     * @param snapshot The snapshot to compare
     * @param newValue The value to set
     * @return True if value set, false if snapshot does not match
     */
    @Override public boolean compareAndSet(Snapshot<V> snapshot, V newValue) {
        return compareAndSet(snapshot.getVersion(), newValue);
    }

    /**
     * Sets value and increments version.
     * @param newValue The value to set
     */
    @Override public final void setValue(V newValue) {
        versionedValue.updateAndGet(prev -> new VersionedValue<>(prev.getVersion() + 1, newValue));
    }

    /** {@inheritDoc} */
    @Override public V getValue() {
        return versionedValue.get().getValue();
    }

    /** {@inheritDoc} */
    @Override public long getVersion() {
        return versionedValue.get().getVersion();
    }

    /** Encapsulates the version and variable value */
    private static class VersionedValue<V> {
        long version;
        V value;

        VersionedValue(long version, V value) {
            this.version = version;
            this.value = value;
        }

        public long getVersion() {
            return version;
        }

        public V getValue() {
            return value;
        }
    }
}
