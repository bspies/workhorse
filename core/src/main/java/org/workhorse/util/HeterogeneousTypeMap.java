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
package org.workhorse.util;


import javax.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for storing, looking up
 * values of heterogeneous types, per the
 * <i>Typesafe Heterogeneous Container</i> pattern
 * in Joshua Bloch's "Effective Java".
 */
public class HeterogeneousTypeMap {

    private Map<Class<?>, Object> typeMap;

    public HeterogeneousTypeMap() {
        typeMap = new HashMap<>();
    }

    public HeterogeneousTypeMap(Provider<Map<Class<?>,Object>> mapProvider) {
        typeMap = mapProvider.get();
    }

    /** Gets the value by its type */
    public <T> T get(Class<T> key) {
        if(key==null) {
            throw new NullPointerException(String.format(
                    "Null keys are not allowed in %s", this.getClass().getName())
            );
        }
        return key.cast(typeMap.get(key));
    }

    /** Puts a value, referenced by its type */
    public <T> void put(Class<T> key, T value) {
        if(key==null) {
            throw new NullPointerException(String.format(
                    "Null keys are not allowed in %s", this.getClass().getName())
            );
        }
        typeMap.put(key, value);
    }
}
