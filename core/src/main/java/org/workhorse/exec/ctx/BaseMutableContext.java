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

import com.google.common.collect.Iterables;
import org.workhorse.type.val.Value;
import org.workhorse.type.var.Variable;

import java.util.Map;

/**
 * Base implementation of {@link MutableContext}.
 *
 * @author Brennan Spies
 */
abstract class BaseMutableContext<VAR extends Variable> extends BaseContext implements MutableContext {

    /**
     * Returns the map of variable values.
     * @return The variables map
     */
    protected abstract Map<String,VAR> getVariables();

    /** {@inheritDoc} */
    @Override public Iterable<String> getNames() {
        return Iterables.concat(getConstants().keySet(), getVariables().keySet());
    }

    /** {@inheritDoc} */
    @Override public boolean hasSymbol(String name) {
        return getConstants().containsKey(name) ||
                getVariables().containsKey(name);
    }

    /** {@inheritDoc} */
    @Override public boolean hasWritable(String name) {
        return getVariables().containsKey(name);
    }

    /** {@inheritDoc} */
    @Override public <V> Value<V> getReadable(Class<V> type, String name) {
        @SuppressWarnings("unchecked")
        Value<V> val = (Value<V>) getReadable(name);
        if(val!=null && !type.isAssignableFrom(val.getType())) {
            throw new IllegalArgumentException(
                String.format("Variable type %s is not assignable from %s", val.getType(), type)
            );
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override public Value<?> getReadable(String name) {
        boolean isWriteable;
        if(!((isWriteable= hasWritable(name)) || hasConstant(name))) {
            throw new IllegalArgumentException(
                    String.format("Symbol with name '%s' does not exist in this Context.", name)
            );
        }
        return isWriteable ? getVariables().get(name) : getConstants().get(name);
    }

    protected boolean hasConstant(String name) {
        return getConstants().containsKey(name);
    }
}
