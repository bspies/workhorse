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

import org.workhorse.type.Versioned;

/**
 * A sub-interface of {@link Variable} representing
 * variables that are versioned for use in consistency
 * checking, specifically in preventing inconsistent
 * writes to a variable that is shared among different
 * {@link org.workhorse.exec.Execution} instances.
 *
 * @author Brennan Spies
 */
public interface VersionedVariable<V> extends Variable<V>, Versioned {

    /**
     * Returns a snapshot of the versioned variable for comparison purposes.
     * @return The snapshot
     * @see #compareAndSet(Snapshot, Object)
     */
    VariableSnapshot<V> snapshot();

    /**
     * Compares the snapshot to the current version of this variable,
     * and sets the value if the version matches.
     * @param snapshot The versioned snapshot
     * @param newValue The value to set
     * @return True if value was set
     */
    boolean compareAndSet(Snapshot<V> snapshot, V newValue);
}
