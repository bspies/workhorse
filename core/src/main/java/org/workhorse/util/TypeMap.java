/**
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
package org.workhorse.util;

import java.util.Map;
import java.util.HashMap;

/**
 * A "typesafe heterogeneous container". See "Effective Java" (Second Edition), by Joshua Bloch: Item 29.
 */
public class TypeMap
{
    private Map<Class<?>,Object> typeToInstances = Maps.newHashMap();

    /**
     * Puts an instance corresponding to the type key in
     * the map.
     * @param type The type key
     * @param instance The instance
     */
    public <T> void put(Class<T> type, T instance) {
        typeToInstances.put(type, instance);
    }

    /**
     * Retrieves the instance of the given type.
     * @param type The type key
     * @return The instance corresponding to that type
     */
    public <T> T get(Class<T> type) {
        return type.cast(typeToInstances.get(type));
    }

    /**
     * Removes the mapping for the given type.
     * @param type The key
     * @return The existing value for the type key that is removed, or null
     */
    public <T> T remove(Class<T> type) {
        return type.cast(typeToInstances.remove(type));
    }
}
