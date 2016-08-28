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
package org.workhorse.graph.exec;

import com.google.common.collect.Iterables;
import org.workhorse.activity.Activity;
import org.workhorse.event.EventType;
import org.workhorse.event.ThrownEvent;
import org.workhorse.event.handler.Catcher;
import org.workhorse.graph.BaseNode;
import org.workhorse.graph.Diagram;
import org.workhorse.graph.Lane;
import org.workhorse.type.Parameter;

import java.util.*;

/**
 * Base class for all nodes in a graph that represent workflow activities.
 *
 * @author Brennan Spies
 */
public abstract class ActivityNode<T extends Activity> extends BaseNode {

    private Map<String,Parameter<?>> inputSet, outputSet;
    private CatcherMap catchers;

    /**
     * Constructor takes parent diagram and lane to which the node
     * belongs.
     *
     * @param id The node id
     * @param diagram the parent diagram
     * @param lane the lane
     */
    protected ActivityNode(String id, Diagram diagram, Lane lane) {
        super(id, diagram, lane);
        inputSet = new HashMap<>();
        outputSet = new HashMap<>();
    }

    /**
     * Does this node represent an atomic (non-composite)
     * activity?
     * @return True if activity is atomic
     */
    public boolean isAtomic() {
        return true;
    }

    /**
     * Returns the runtime instance type, a that this node produces
     * during execution. The type should generally be the
     * most specific sub-interface of {@link Activity}.
     * @return The instance type
     */
    public abstract Class<T> getInstanceType();

    /**
     * Returns the input set for the activity. This is the set
     * of parameters that are evaluated when creating the initial
     * variables in the activity's context.
     * @return The input set of parameters
     */
    public Collection<Parameter<?>> getInputSet() {
        return Collections.unmodifiableCollection(inputSet.values());
    }

    /**
     * Adds the input parameter to the activity node. Argument must not
     * represent a duplicate parameter, i.e. have the same name as an existing
     * input parameter.
     * @param inputParam The input parameter
     * @throws IllegalArgumentException If a duplicate name is found
     */
    public void addInput(Parameter<?> inputParam) {
        if(inputSet.containsKey(inputParam.getName())) {
            throw new IllegalArgumentException("Cannot add input parameter with duplicate name: "
                    + inputParam.getName());
        }
        inputSet.put(inputParam.getName(), inputParam);
    }

    /**
     * Returns the output set for the activity. This is the set of
     * parameters that are evaluated when the activity is completed
     * to create/modify the appropriate variables in the parent's
     * context.
     * @return The output set of parameters
     */
    public Collection<Parameter<?>> getOutputSet() {
        return Collections.unmodifiableCollection(outputSet.values());
    }

    /**
     * Adds the output parameter to the activity node. Argument must not
     * represent a duplicate parameter, i.e. have the same name as an existing
     * output parameter.
     * @param outputParam The output parameter
     * @throws IllegalArgumentException If a duplicate name is found
     */
    public void addOutput(Parameter<?> outputParam) {
        if(outputSet.containsKey(outputParam.getName())) {
            throw new IllegalArgumentException("Cannot add output parameter with duplicate name: "
                    + outputParam.getName());
        }
        outputSet.put(outputParam.getName(), outputParam);
    }

    /**
     * Sets a handler for a given event.
     * @param catcher The handler to set
     */
    public void setCatcher(Catcher<? extends ThrownEvent<?>> catcher) {
        if(catchers==null) {
            catchers = new CatcherMap();
        }
        catchers.put(catcher);
    }

    /**
     * Returns the event catcher for the given event if it exists for
     * this activity node.
     * @param <E> The event type
     * @param event The event to be matched
     * @return The catcher, or null if none
     */
    public <E extends ThrownEvent<E>> Catcher<E> getCatcher(E event) {
        if(catchers==null) return null;
        return catchers.get(event);
    }


    /**
     * Map that holds EventType->Catcher mappings.
     */
    private static class CatcherMap {

        private Map<EventType<?>, Catcher<?>> catchers;

        CatcherMap() {
            catchers = new HashMap<>();
        }

        /**
         * Places a <code>Catcher</code> in the map.
         *
         * @param catcher The catcher to put
         */
        void put(Catcher<?> catcher) {
            catchers.put(catcher.forType(), catcher);
        }

        @SuppressWarnings("unchecked")
        <E extends ThrownEvent<E>> Catcher<E> get(E event) {
            Catcher<E> catcher = (Catcher<E>) catchers.get(event.getType());
            if (catcher != null && catcher.matches(event)) {
                return catcher;
            } else {
                return null;
            }
        }
    }
}
