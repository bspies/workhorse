/*
 * Copyright (c) 2009-2016 Brennan Spies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.workhorse.process;

import org.workhorse.exec.Continuation;
import org.workhorse.exec.Execution;
import org.workhorse.exec.ExecutionFactory;
import org.workhorse.exec.RunResult;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.ctx.ReadOnlyContext;
import org.workhorse.exec.ctx.ReadValues;
import org.workhorse.exec.ctx.VersionedContext;
import org.workhorse.flow.Controller;
import org.workhorse.flow.ExecutionController;
import org.workhorse.graph.Node;
import org.workhorse.graph.ProcessDiagram;
import org.workhorse.id.IdGenerator;
import org.workhorse.service.ServiceManager;
import org.workhorse.type.val.Value;
import org.workhorse.util.Version;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Implementation of {@link Process} that runs locally in a single JVM instance.
 *
 * @author Brennan Spies
 */
public class WorkflowProcess extends BaseContainer implements Process {

    private ProcessDiagram diagram;
    private Environment environment;
    private ServiceManager serviceManager;
    private ConcurrentMap<UUID,ExecutionTracker> runningExecutions;
    private ConcurrentMap<UUID,Execution> pausedExecutions;
    private LocalDateTime creationDate, completionDate;
    private Version version;
    private VersionedContext context;

    /**
     * The default constructor.
     *
     * @param id The process id
     * @param diagram The process diagram
     * @param env The process environment
     */
    public WorkflowProcess(UUID id, ProcessDiagram diagram, Environment env) {
        super(id);
        this.diagram = diagram;
        this.environment = env;
        this.version = diagram.getVersion();
        this.context = initContext(env.getInitialValues());
        this.serviceManager = getDependency(ServiceManager.class);
        runningExecutions = new ConcurrentHashMap<>();
        pausedExecutions = new ConcurrentHashMap<>();
        creationDate = LocalDateTime.now();
        setState(State.READY);
    }

    /** {@inheritDoc} */
    @Override public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /** {@inheritDoc} */
    @Override public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    /** {@inheritDoc} */
    @Override public void start() {
        setState(State.RUNNING);
        ExecutorService executorService = getDependency(ExecutorService.class);
        ExecutionFactory execFactory = getDependency(ExecutionFactory.class);
        diagram.getStartingNodes().forEach(node -> {
            Execution execution = execFactory.create(this);
            Future<?> future = executorService.submit(new ExecutionRunner(node, execution));
            runningExecutions.put(execution.getId(), new ExecutionTracker(execution, future));
        });
    }

    /** {@inheritDoc} */
    @Override public void cancel() {
        //todo implement
        completionDate = LocalDateTime.now();
        setState(State.CANCELLED);
    }

    /** {@inheritDoc} */
    @Override public void end() {
        //todo implement
        completionDate = LocalDateTime.now();
        setState(State.COMPLETED);
    }

    /** {@inheritDoc} */
    @Override public Context getContext() {
        return ReadOnlyContext.wrap(context);
    }

    /** {@inheritDoc} */
    @Override public Version getVersion() {
        return version;
    }

    /** {@inheritDoc} */
    @Override public <T> T getService(Class<T> serviceType) {
        return serviceManager.getService(serviceType);
    }

    /*============================================================================
     * Private methods and inner classes
     *===========================================================================*/

    /**
     * Gets the dependency by its type.
     * @param type The dependency type
     * @return The instance
     */
    private <T> T getDependency(Class<T> type) {
        return environment.getDependencyManager().getInstance(type);
    }

    /**
     * Creates the process context with the initial values.
     * @param initValues The initial values
     * @return The context
     */
    private VersionedContext initContext(Iterable<Value<?>> initValues) {
        //create context
        initValues.forEach(value -> {});
        return null; //todo implement
    }

    /**
     * Processes the result of a given execution run.
     * @param executionId The id of the execution
     * @param result The execution run result
     */
    private void processResult(UUID executionId, RunResult result) {
        ExecutionTracker executionTracker = runningExecutions.remove(executionId);
        switch (result.getState()) {
            case TERMINATED:
                break;
            case PAUSED:
                Execution exec = executionTracker.getExecution();
                pausedExecutions.put(exec.getId(), exec);
                Continuation continuation = new Continuation(executionTracker.getExecution(), result.getCurrentNode());
                //todo handle continuation
                break;
            case ERROR:
                //todo deal with unhandled error
                break;
        }
    }

    @Override
    protected void importValues(Iterable<Value<?>> inputValues, ReadValues snapshotValues) {
        inputValues.forEach(value -> context.createOrUpdateWritable(value, snapshotValues::getSnapshot));
    }

    /**
     * {@link Runnable} implementation for running {@link Execution} instances.
     */
    private class ExecutionRunner implements Runnable {

        private final Node node;
        private final Execution execution;

        ExecutionRunner(Node node, Execution execution) {
            this.node = node;
            this.execution = execution;
        }

        @Override
        public void run() {
            Controller controller = new ExecutionController(node.getParent());
            RunResult result = controller.run(node, execution);
            processResult(execution.getId(), result);
        }
    }

    /** Tracks progress of execution */
    private static class ExecutionTracker {

        private final Execution execution;
        private final Future<?> tracker;

        ExecutionTracker(Execution execution, Future<?> tracker) {
            this.execution = execution;
            this.tracker = tracker;
        }

        Execution getExecution() {
            return execution;
        }

        Future<?> getTracker() {
            return tracker;
        }
    }

    /**
     *  {@code ProcessFactory} implementation for creating
     *  {@code WorkflowProcess} instances.
     */
    public static class Factory implements ProcessFactory {

        private Environment environment;
        private IdGenerator<UUID> idGenerator;

        @Inject
        public Factory(IdGenerator<UUID> idGenerator, Environment environment) {
            this.environment = environment;
            this.idGenerator = idGenerator;
        }

        @Override
        public Process createProcess(ProcessDiagram processDiagram, Value<?>... inputValues) {
            environment.setInitialValues(inputValues);
            return new WorkflowProcess(idGenerator.generate(), processDiagram, environment);
        }
    }
}
