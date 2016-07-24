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
package org.workhorse.exec.ctx;

import org.workhorse.type.val.Value;
import org.workhorse.type.var.Variable;
import org.workhorse.type.Variables;

/**
 * A context for wrapping mutable contexts to prevent
 * modification.
 *
 * @author Brennan Spies
 */
public class ReadOnlyContext implements Context {

    private MutableContext wrappedContext;

    private ReadOnlyContext(MutableContext mutableContext) {
        this.wrappedContext = mutableContext;
    }

    /**
     * Factory method for creating an immutable context.
     * @param mutableContext The wrapped context
     * @return The immutable context
     */
    public static Context wrap(MutableContext mutableContext) {
        return new ReadOnlyContext(mutableContext);
    }

    @Override
    public <V> Value<V> getReadable(Class<V> type, String name) {
        return readOnly(wrappedContext.getReadable(type, name));
    }

    @Override
    public Value<?> getReadable(String name) {
        return readOnly(wrappedContext.getReadable(name));
    }

    private <V> Value<V> readOnly(Value<V> symbol) {
        return symbol==null ? null :
                (symbol instanceof Variable ? Variables.readOnly((Variable<V>) symbol) : symbol);
    }

    @Override
    public boolean hasSymbol(String name) {
        return wrappedContext.hasSymbol(name);
    }

    @Override
    public Iterable<String> getNames() {
        return wrappedContext.getNames();
    }
}
