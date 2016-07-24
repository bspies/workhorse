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
package org.workhorse.util;

/**
 * A simple, immutable key-value pair.
 *
 * @author Brennan Spies
 */
public class KeyValuePair<K,V> {

    private K key;
    private V value;

    /** Creates the key-value pair */
    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /** Gets the key */
    public K getKey() {
        return key;
    }

    /** Gets the value */
    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyValuePair<?, ?> that = (KeyValuePair<?, ?>) o;

        if (!key.equals(that.key)) return false;
        return value.equals(that.value);

    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
