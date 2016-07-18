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

/**
 * Implementation of a mutable {@link Variable} that does not
 * mutate "in-place" but instead keeps a full history of every
 * set value.
 */
public class TimeBasedVariable<V> extends BaseVariable<V> implements Variable<V> {

    /**
     * Creates a time-based variable with an initial value.
     *
     * @param name The variable name
     * @param type The variable type
     * @param initialValue The initial value
     */
    public TimeBasedVariable(String name, Class<V> type, V initialValue) {
        super(name, type);
    }

    @Override
    public V getValue() {
        return null;
    }

    @Override
    public void setValue(V value) {

    }
}
