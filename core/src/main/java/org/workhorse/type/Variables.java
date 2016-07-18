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
package org.workhorse.type;

import org.workhorse.type.val.Constant;
import org.workhorse.type.val.Value;
import org.workhorse.type.var.ConsistentVariable;
import org.workhorse.type.var.MutableVariable;
import org.workhorse.type.var.Variable;
import org.workhorse.type.var.VersionedVariable;

/**
 * Utility class for dealing with {@link Value} and
 * {@link Variable} types.
 */
public class Variables {

    /**
     *
     * @param name The symbol name
     * @param type The value type
     * @param value The underlying value
     * @return
     */
    public static <V> Value<V> valueOf(String name, Class<V> type, V value) {
        return new Constant<>(name, type, value);
    }

    /**
     * Returns the value for the given name and underlying value.
     * @param name The symbol name
     * @param value The underlying value
     * @return The value
     */
    public static <V> Value<V> valueOf(String name, V value) {
        return valueOf(name, (Class<V>)value.getClass(), value);
    }

    /**
     * Creates a read-only version of the given variable.
     * @param variable The variable to make read-only
     * @return The read-only variable
     */
    public static <V> Value<V> readOnly(Variable<V> variable) {
        return new Value<V>() {
            @Override public String getName() {return variable.getName();}
            @Override public Class<V> getType() {return variable.getType();}
            @Override public V getValue() {return variable.getValue();}
        };
    }

    /**
     * Ensures the the passed {@code Value} is a constant, i.e. read-only. Will wrap
     * any variable appropriately.
     * @param possibleVar Possible variable reference
     * @return The read-only value
     */
    public static <V> Value<V> ensureConstant(Value<V> possibleVar) {
        return possibleVar instanceof Variable ? readOnly((Variable<V>) possibleVar) : possibleVar;
    }

    /**
     * Creates a versioned variable from the given value.
     * @param value The value
     * @return The versioned variable
     */
    public static <V> VersionedVariable<V> versionedVariable(Value<V> value) {
        return new ConsistentVariable<>(value.getName(), value.getType(), value.getValue());
    }

    /**
     * Creates a mutable variable from the given value.
     * @param value The value
     * @return The variable
     */
    public static <V> Variable<V> writable(Value<V> value) {
        return new MutableVariable<>(value);
    }
}
