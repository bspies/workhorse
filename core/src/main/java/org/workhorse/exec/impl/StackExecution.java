/**
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
package org.workhorse.exec.impl;

import java.io.Serializable;
import java.util.Collection;

import org.workhorse.Element;
import org.workhorse.activity.Action;
import org.workhorse.activity.Task;
import org.workhorse.actor.User;
import org.workhorse.exec.*;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.ctx.ActivityContext;
import org.workhorse.exec.ctx.ExecutionContext;
import org.workhorse.exec.ctx.LocalContext;
import org.workhorse.process.Container;
import org.workhorse.var.Parameter;
import org.workhorse.graph.control.NodeControl;
import org.workhorse.graph.node.ActionNode;
import org.workhorse.graph.node.ActivityNode;
import org.workhorse.graph.node.EventNode;
import org.workhorse.graph.node.Node;
import org.workhorse.graph.node.SubProcessNode;
import org.workhorse.graph.node.TaskNode;
import org.workhorse.notification.Notice;
import org.workhorse.notification.Notification;
import org.workhorse.persistence.Persistence;
import org.workhorse.process.Process;
import org.workhorse.process.SubProcess;
import org.workhorse.service.Service;
import org.workhorse.struct.ArrayStack;
import org.workhorse.var.QualifiedName;

import javax.annotation.Nullable;

/**
 * A stack-based implementation of {@link Execution}.
 *
 * @author Brennan Spies
 */
public class StackExecution extends Element implements Execution
{
    //root will have no parent
	private @Nullable StackExecution parent;
    //non-null if execution is paused
	private @Nullable Continuation continuation;
    private ExecutionContext executionContext;
	private ArrayStack<Executable> callStack;

    //"pointers" to the process diagram
    private Node currentNode;
	private Phase currentPhase;

    /**
     * No-arg constructor for internal use only.
     */
    public StackExecution() {}
	
	/**
	 * Default constructor for top-level {@code Execution}.
     * @param process The parent process
	 */
	public StackExecution(org.workhorse.process.Process process) {
        executionContext = new ExecutionContext();
		callStack = new ArrayStack<Executable>();
		callStack.push(process);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Execution createChild()  {
		StackExecution exec = null;
		try {
			exec = (StackExecution)this.clone();
            exec.parent = this;
		} catch (CloneNotSupportedException e) { /* Won't happen */ }
		return exec;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Context<QualifiedName> getExecutionContext() {
		return executionContext;
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
	 * {@inheritDoc}
	 */
	public Process getProcess() {
		Executable executable = callStack.peek(0);
		if(executable instanceof Process)
			return (Process)executable;
		throw new IllegalStateException("Bottom of call stack is not a " + Process.class.getName());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Nullable public Execution getParent() {
		return parent;
	}

    //Gets the service of the given type
    private <S extends Service> S getService(Class<S> serviceType) {
        return getProcess().getService(serviceType);
    }
	
	/**
	 * Creates a new context for the block.
	 * @param block The block for which the context will be created
	 * @return The new context
	 */
	private ActivityContext createContext(Block block) {
		Context<QualifiedName> parentContext = getExecutionContext();
		ActivityContext newContext = new ActivityContext();	//TODO inject
		for(Parameter<?> param : block.getInputSet()) {
			newContext.setVariable(param.getName(),
                    param.getExpression().evaluate(parentContext));
		}
		return newContext;
	}
	
	/**
	 * Releases the current context (current top of stack), but before that sets
	 * the output variables on the executable context (next top of stack).
	 * @param block The block with which the current context is associated
	 */
	private void exitContext(Block block) {
		/*Context child = callStack.pop().getContext();
		Context parent = callStack.peek().getContext();
		for(Parameter<Serializable> param : block.getOutputSet()) {
			parent.setVariable(param.getName(),
                    param.getExpression().evaluate(child));
		}*/
	}
	
	/**
	 * Returns the control context or creates a new one.
	 * @param node The activity node
	 * @return The context
	 */
	private ActivityContext getContext(ActivityNode node) {
        Executable executable = callStack.peek();
		return (callStack.peek() instanceof ControlFrame) ?
			((ControlFrame)executable).getContext() : createContext(node);
	}
	
	/**
	 * {@inheritDoc}
	 */
    public void enter(Node node) {
		this.currentNode = node;
		setPhase(Phase.ENTER);
		if(node instanceof ActivityNode) {
			ActivityNode actNode = (ActivityNode)node;
			NodeControl control = actNode.getControl();
			if(control!=null) {
				Context newContext = createContext(actNode);
				//callStack.push(new ControlFrame(newContext));
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void leave(Node node) {
		this.currentPhase = Phase.LEAVE;
		if(callStack.peek() instanceof ControlFrame) {
			exitContext((Block)node);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void end() {
		//TODO implement Execution.end()
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void pause() {
		pause(currentNode, currentPhase);
	}
	
	/**
	 * {@inheritDoc}
     * @param node
     * @param phase
     */
	public void pause(Node node, Phase phase) {
		this.continuation = new Continuation(this, currentNode, currentPhase);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isPaused() {
		return continuation!=null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void resume() {
		if(!isPaused()) {
			throw new IllegalStateException("Cannot resume execution that is not paused");
        }
		//restore transient state
		this.currentNode = getProcess().getNode(continuation.getNodeId());
		this.currentPhase = continuation.getPhase();
		//remove paused state prior to re-entry
        getService(Persistence.class).remove(continuation);
		continuation = null;
        currentNode.getParent().getController()
            .reenter(this, currentNode, currentPhase);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void process(ActionNode actionNode) {
		setPhase(Phase.PROCESS);
        LocalContext actionContext = (LocalContext)getContext(actionNode);   //FIXME
		Action action = actionNode.getActionFactory().createAction();
        //callStack.push(new ActionFrame(actionContext, action));
        action.execute(actionContext);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void postProcess(ActivityNode activityNode) {
		setPhase(Phase.POST_PROCESS);
		if(!(callStack.peek() instanceof ControlFrame))
			exitContext(activityNode);
		else
			callStack.pop();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void process(TaskNode taskNode) {
		setPhase(Phase.PROCESS);
		ActivityContext context = getContext(taskNode);
		//create task instance
		Task task = getContainer()
			.getActivityFactory()
			.createTask(taskNode, context, this);
		callStack.push(task);
		
		//Allocate responsible users
		Collection<User> users = taskNode.getDefinition()
			.getAllocation()
			.determine(context);
		if(users.size()==0) {
			//TODO error
		}
		//Send notice of new task to each user
        Notice notice = null; //TODO
        Notification ns = getService(Notification.class);
		for(User user : users) {
			ns.send(user, notice);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void process(SubProcessNode processNode) {
		setPhase(Phase.PROCESS);
		ActivityContext context = getContext(processNode);
		//create subprocess instance
		SubProcess subProcess = getContainer().getActivityFactory()
			.createSubProcess(processNode, context);
		callStack.push(subProcess);
		//TODO persist subprocess
		
		//start subprocess
		subProcess.start(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public void process(EventNode eventNode) {
		setPhase(Phase.PROCESS);
		eventNode.doEvent(this);
	}

    /**
     * Sets the current phase of the execution at a node.
     * @param phase The execution phase
     */
    private void setPhase(Phase phase) {
        this.currentPhase = phase;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
