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
package org.workhorse.dependency.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import org.workhorse.activity.Activity;
import org.workhorse.activity.HumanTask;
import org.workhorse.activity.Script;
import org.workhorse.activity.Task;
import org.workhorse.activity.factory.ActivityCreate;
import org.workhorse.activity.factory.ActivityFactory;
import org.workhorse.activity.factory.DefaultActivityFactory;
import org.workhorse.exec.ctx.VersionedContext;
import org.workhorse.graph.exec.ScriptNode;
import org.workhorse.id.IdGenerator;
import org.workhorse.id.InstanceIdGenerator;
import org.workhorse.process.*;
import org.workhorse.runtime.Engine;
import org.workhorse.runtime.WorkflowEngine;

import javax.inject.Provider;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Configuration of core workflow functionality for the
 * {@link org.workhorse.dependency.GuiceDependencyManager}.
 */
public class CoreConfiguration extends AbstractModule {

    private final TypeLiteral<IdGenerator<UUID>> UUID_GEN =
            new TypeLiteral<IdGenerator<UUID>>(){};
    private final TypeLiteral<Class<? extends Activity>> ACTIVITY_TYPE =
            new TypeLiteral<Class<? extends Activity>>() {};
    private final TypeLiteral<ActivityCreate<? extends Activity>> ACTIVITY_CREATE_TYPE =
            new TypeLiteral<ActivityCreate<? extends Activity>>() {};

    @Override protected void configure() {
        bind(UUID_GEN).to(InstanceIdGenerator.class).asEagerSingleton();
        bind(Engine.class).to(WorkflowEngine.class).asEagerSingleton();
        bind(ExecutorService.class).toInstance(Executors.newCachedThreadPool());
        bind(Environment.class).to(ProcessEnvironment.class).asEagerSingleton();
        bind(ProcessFactory.class).to(WorkflowProcess.Factory.class);
        bind(ActivityFactory.class).to(DefaultActivityFactory.class);
        bind(DefaultActivityFactory.FunctionMap.class).toProvider(FunctionMapProvider.class);
    }

    /** Maps the activity subtypes to creation functions for them. */
    private static class FunctionMapProvider implements Provider<DefaultActivityFactory.FunctionMap> {
        @Override public DefaultActivityFactory.FunctionMap get() {
            DefaultActivityFactory.FunctionMap functionMap = new DefaultActivityFactory.FunctionMap();
            functionMap.put(Task.class, (instanceId, node, ctx) ->
                    new HumanTask(instanceId, node.getName(), ctx));
            functionMap.put(Script.class, (instanceId, node, ctx) ->
                    new Script(instanceId, node.getName(), ctx, ((ScriptNode)node).getScriptReference()));
            functionMap.put(SubProcess.class, (instanceId, node, ctx) ->
                    new CallableSubProcess(instanceId, (VersionedContext) ctx));
            return functionMap;
        }
    }
}
