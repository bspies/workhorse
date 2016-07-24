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
 * An {@code Attribute} is essentially
 * a key-value pair where the key is always
 * a {@code String}.
 */
public class Attribute<V> extends KeyValuePair<String,V> {

    /**
     * A wildcard attribute that will match any other attribute.
     */
    public static final Attribute<String> ANY = new Attribute<>("*", "*");

    /**
     * Constructs an attribute.
     * @param name The attribute name
     * @param value The attribute value
     */
    public Attribute(String name, V value) {
        super(name, value);
    }

    /**
     * Does the given attribute match this attribute?
     * This will always return true if the given attribute
     * is {@link Attribute#ANY}.
     * @param attr The attribute to match
     * @return True if the attributes match
     */
    public boolean matches(Attribute<?> attr) {
        return this.equals(attr) || attr.equals(ANY);
    }
}
