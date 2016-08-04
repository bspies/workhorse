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

import org.workhorse.type.InconsistentWriteException;
import org.workhorse.type.val.Value;
import org.workhorse.type.Variables;
import org.workhorse.type.var.VersionedVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A context that is mutable and potentially shared by multiple {@link org.workhorse.exec.Execution}
 * instances.
 *
 * @author Brennan Spies
 */
public class SharedMutableContext extends BaseMutableContext<VersionedVariable> implements VersionedContext {

    private Map<String,Value> constants;
    private ConcurrentMap<String,VersionedVariable> variables;

    /**
     * Creates a shared, mutable context.
     */
    public SharedMutableContext() {
        constants = new HashMap<>();
        variables = new ConcurrentHashMap<>();
    }

    /**
     * Creates a shared, mutable context with initial constants and
     * variables.
     * @param initConstants The initial constants
     * @param initVars The initial variables
     */
    public SharedMutableContext(Iterable<Value> initConstants, Iterable<Value> initVars) {
        this();
        initConstants.forEach(value -> constants.put(value.getName(), Variables.ensureConstant(value)));
        initVars.forEach(value -> variables.put(value.getName(), Variables.versionedVariable(value)));
    }

    /** Variables */
    @Override protected Map<String,VersionedVariable> getVariables() {
        return variables;
    }

    /** Constants */
    @Override protected Map<String,Value> getConstants() {
        return constants;
    }

    /** {@inheritDoc} */
    @Override
    public <V> VersionedVariable<V> getVariable(Class<V> type, String name) {
        @SuppressWarnings("unchecked")
        VersionedVariable<V> var = (VersionedVariable<V>) getVariable(name);
        if(var!=null && !type.isAssignableFrom(var.getType())) {
            throw new IllegalArgumentException(
                    String.format("Variable type %s is not assignable from %s", var.getType(), type)
            );
        }
        return var;
    }

    /** {@inheritDoc} */
    @Override public VersionedVariable<?> getVariable(String name) {
        return getVariables().get(name);
    }

    /** {@inheritDoc} */
    @Override @SuppressWarnings("unchecked")
    public <V> VersionedVariable<V> createWritable(Value<V> value) {
        assertNoConstant(value.getName());
        VersionedVariable<V> var;
        if(getVariables().putIfAbsent(value.getName(), var=Variables.versionedVariable(value))!=null) {
            throw new IllegalArgumentException(
                    String.format("Variable with name '%s' already exists in this Context.", value.getName())
            );
        }
        return var;
    }

    /** {@inheritDoc} */
    @Override @SuppressWarnings("unchecked")
    public <V> VersionedVariable<V> createOrUpdateWritable(Value<V> value, RetrieveSnapshotCallback callback) {
        assertNoConstant(value.getName());
        VersionedVariable<V> createdVar, updatedVar;
        if((updatedVar=getVariables().putIfAbsent(value.getName(), createdVar=Variables.versionedVariable(value)))!=null) {
            if(!updatedVar.compareAndSet(callback.retrieve(value.getType(), value.getName()), value.getValue())) {
                //snapshot version doesn't match current version
                throw new InconsistentWriteException(
                        value.getName(),
                        value.getValue(),
                        updatedVar.getValue(),
                        "Read variable version does not match current variable version."
                );
            }
            return updatedVar;
        } else {
            return createdVar;
        }
    }

    /** Throws exception if constant with given name exists already */
    private void assertNoConstant(String name) {
        if(hasConstant(name)) {
            throw new IllegalArgumentException(
                    String.format("Constant with name '%s' already exists in this Context.", name)
            );
        }
    }
}
