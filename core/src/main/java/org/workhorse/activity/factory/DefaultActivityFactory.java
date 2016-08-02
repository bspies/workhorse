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
package org.workhorse.activity.factory;

import org.workhorse.activity.Activity;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.ctx.MutableContext;
import org.workhorse.graph.exec.ActivityNode;
import org.workhorse.id.IdGenerator;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Default implementation of {@link ActivityFactory}.
 *
 * @author Brennan Spies
 */
public class DefaultActivityFactory implements ActivityFactory {

    private IdGenerator<UUID> idGenerator;
    private FunctionMap functionMap;

    public DefaultActivityFactory(IdGenerator<UUID> idGenerator) {
        this(idGenerator, FunctionMap::new);
    }

    @Inject
    public DefaultActivityFactory(IdGenerator<UUID> idGenerator, Provider<FunctionMap> functionProvider) {
        this.idGenerator = idGenerator;
        this.functionMap = functionProvider.get();
    }

    /** {@inheritDoc} */
    @Override public <T extends Activity> T createActivity(ActivityNode<T> node, Context ctx) {
        Class<T> interfaceType = node.getInstanceType();
        CreateActivity<T> createFunction = functionMap.get(interfaceType);
        return createFunction.create(idGenerator.generate(), node, createActivityContext(node, ctx));
    }

    private MutableContext createActivityContext(ActivityNode<?> node, Context parent) {
        node.getInputSet().forEach(param -> {

        });
        return null; //todo
    }

    /**
     * Utility class for mapping the activity create functions.
     */
    public static class FunctionMap {

        private Map<Class<? extends Activity>,CreateActivity<? extends Activity>> map;

        public FunctionMap() {
            map = new HashMap<>();
        }

        <A extends Activity> CreateActivity<A> get(Class<A> activityType) {
            assertNotNull(activityType);
            @SuppressWarnings("unchecked")
            CreateActivity<A> createFunction = (CreateActivity<A>) map.get(activityType);
            if(createFunction==null) {
                throw new IllegalArgumentException(String.format("No create function exists for %s", activityType));
            }
            return createFunction;
        }

        public <A extends Activity> void put(Class<A> activityType, CreateActivity<A> createFunction) {
            assertNotNull(activityType);
            map.put(activityType, createFunction);
        }

        private void assertNotNull(Class<?> c) {
            if(c==null) {
                throw new NullPointerException(String.format(
                        "Null keys are not allowed in %s", this.getClass().getName())
                );
            }
        }
    }
}
