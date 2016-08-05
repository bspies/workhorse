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
package org.workhorse.process;

import org.workhorse.activity.Activity;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.ctx.ReadValues;
import org.workhorse.graph.exec.ActivityNode;
import org.workhorse.type.val.Value;
import org.workhorse.type.Variables;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Abstract base implementation of the {@link Container} interface.
 *
 * @author Brennan Spies
 */
abstract class BaseContainer extends ProcessElement implements Container {

    private State state = State.READY;
    private String name, description;

    BaseContainer(UUID id) {
        super(id);
    }

    /** {@inheritDoc} */
    @Override public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override public void setName(String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override public String getDescription() {
        return description;
    }

    /** {@inheritDoc} */
    @Override public void setDescription(String description) {
        this.description = description;
    }

    /** {@inheritDoc} */
    @Override public State getState() {
        return state;
    }

    /**
     * Sets the state of the {@link Container}.
     * @param state The new state
     */
    protected void setState(State state) {
        this.state = state;
    }

    /** {@inheritDoc} */
    @Override public boolean isActive() {
        return state == State.RUNNING;
    }

    /** {@inheritDoc} */
    @Override public <T extends Activity>void evaluateOutput(T activity, ActivityNode<T> activityNode) {
        Context activityCtx = activity.getContext();
        Iterable<Value<?>> inputVals = activityNode.getOutputSet()
                .stream()
                .map(param -> Variables.valueOf(param.getName(), param.evaluate(activityCtx)))
                .collect(Collectors.toCollection(ArrayList::new));
        importValues(inputVals, activity.getReadParentValues());
    }

    /**
     * Imports the value in the container context.
     * @param inputValues The values to import
     * @param snapshotValues The snapshot values read from parent
     */
    abstract protected void importValues(Iterable<Value<?>> inputValues, ReadValues snapshotValues);
}
