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

import org.workhorse.type.val.Value;
import org.workhorse.type.var.Variable;
import org.workhorse.type.Variables;

import java.util.HashMap;
import java.util.Map;

/**
 * A mutable context that is only acted upon by a single
 * {@link org.workhorse.exec.Execution} instance.
 */
public class UnsharedMutableContext extends BaseMutableContext<Variable> {

    private Map<String,Value> constants;
    private Map<String,Variable> variables;

    public UnsharedMutableContext() {
        this.constants = new HashMap<>();
        this.variables = new HashMap<>();
    }

    @Override
    protected Map<String,Variable> getVariables() {
        return variables;
    }

    @Override
    protected Map<String, Value> getConstants() {
        return constants;
    }

    /** {@inheritDoc} */
    @Override public <V> Variable<V> getWritable(Class<V> type, String name) {
        @SuppressWarnings("unchecked")
        Variable<V> var = (Variable<V>) getWritable(name);
        if(var!=null && !type.isAssignableFrom(var.getType())) {
            throw new IllegalArgumentException(
                    String.format("Variable type %s is not assignable from %s", var.getType(), type)
            );
        }
        return var;
    }

    /** {@inheritDoc} */
    @Override public Variable<?> getWritable(String name) {
        return getVariables().get(name);
    }

    /** {@inheritDoc} */
    @Override public <V> Variable<V> createWritable(Value<V> value) {
        Variable<V> var = Variables.writable(value);
        getVariables().put(value.getName(), var);
        return var;
    }
}
