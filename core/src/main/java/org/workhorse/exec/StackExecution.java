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
package org.workhorse.exec;

import org.workhorse.activity.Activity;
import org.workhorse.activity.factory.ActivityFactory;
import org.workhorse.exec.ctx.Context;
import org.workhorse.graph.event.EventNode;
import org.workhorse.graph.exec.ActivityNode;
import org.workhorse.id.IdGenerator;
import org.workhorse.process.Container;
import org.workhorse.process.Process;
import org.workhorse.process.ProcessElement;
import org.workhorse.struct.ArrayStack;
import org.workhorse.struct.Stack;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Stack-based implementation of an {@link Execution}.
 *
 * @author Brennan Spies
 */
public class StackExecution extends ProcessElement implements Execution {

    private Stack<Contextual> callStack;
    private Process process;

    /**
     * Creates a new {@code StackExecution}.
     *
     * @param id The execution id
     * @param process The parent process
     */
    public StackExecution(UUID id, Process process) {
        super(id);
        this.process = process;
        callStack = new ArrayStack<>();
        callStack.push(process);
    }

    /** {@inheritDoc} */
    @Override public Context getCurrentContext() {
        return callStack.peek().getContext();
    }

    /** {@inheritDoc} */
    @Override public Process getProcess() {
        return process;
    }

    /** {@inheritDoc} */
    @Override public <T extends Activity> RunState visit(ActivityNode<T> node) {
        RunState state;
        try {
            //create Activity
            ActivityFactory factory = getProcess().getService(ActivityFactory.class); //todo dependency instead?
            T activity = factory.createActivity(node, getCurrentContext());
            callStack.push(activity);
            state = activity.perform(this);
            //if still running, eval output
            if(state==RunState.RUNNING) {
                callStack.pop();
                Container container = (Container) callStack.peek();
                container.evaluateOutput(activity,node);
            }
        } catch (Throwable t) {
            state = RunState.ERROR;
        }
        return state;
    }

    /** {@inheritDoc} */
    @Override public RunState visit(EventNode node) {
        //todo generate and throw event
        return null;
    }

    /** {@inheritDoc} */
    @Override public boolean isPaused() {
        return false;
    }

    /**
     * Returns the closest {@code Container} on the call stack.
     * @return The executable
     */
    private Container getContainer() {
        for(int i=callStack.size()-1; i>=0; i--) {
            if(callStack.peek(i) instanceof Container)
                return (Container)callStack.peek(i);
        }
        throw new IllegalStateException("Unable to find " + Container.class.getName() + " on call stack");
    }

    /**
     * Helper factory for creating stack execution instances.
     */
    public static class Factory implements ExecutionFactory {

        private IdGenerator<UUID> idGenerator;

        @Inject
        public Factory(IdGenerator<UUID> idGenerator) {
            this.idGenerator = idGenerator;
        }

        @Override public Execution create(Process process) {
            return new StackExecution(idGenerator.generate(), process);
        }
    }
}
