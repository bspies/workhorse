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
package org.workhorse.process.impl;

import com.google.inject.Inject;
import org.workhorse.exec.Execution;
import org.workhorse.exec.ctx.ActivityContext;
import org.workhorse.exec.impl.StackExecution;
import org.workhorse.factory.ActivityFactory;
import org.workhorse.graph.ProcessDiagram;
import org.workhorse.graph.node.Node;
import org.workhorse.graph.node.StartingNode;
import org.workhorse.process.Process;
import org.workhorse.process.State;
import org.workhorse.service.Service;
import org.workhorse.service.ServiceManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * The standard implementation of a {@link Process}.
 *
 *  @author Brennan Spies
 */
public class StandardProcess extends BaseContainer implements Process
{
	private ProcessDiagram diagram;
    private ServiceManager services;
	private Collection<Execution> executions;
	private Date creationDate, completionDate;

    /**
     * For internal use only.
     */
    public StandardProcess() {}
	
	/**
	 * Constructs a process that uses the given process diagram.
	 * @param diagram The process diagram
	 * @param svcMgr The service manager
     * @param factory The factory for creating activity instances
	 */
    @Inject
	public StandardProcess(ProcessDiagram diagram, ServiceManager svcMgr, ActivityFactory factory) {
		super(factory, new ActivityContext());      //TODO inject Context
		this.diagram = diagram;
		creationDate = new Date();
		executions = new ArrayList<Execution>();
        services = svcMgr;
	}

	/**
	 * @see org.workhorse.process.Process#start()
	 */
	public void start() {
		setState(State.RUNNING);
		for(StartingNode startNode : diagram.getStartingNodes()) {
			Execution exec = new StackExecution(this);	//TODO inject
			executions.add(exec);
			diagram.getController().start(exec, startNode);
		}
	}
	
	/**
	 * @see org.workhorse.process.Container#cancel()
	 */
	public void cancel() {
		setState(State.CANCELLED);
	}
	
	/**
	 * @see org.workhorse.process.Container#end()
	 */
	public void end() {
		completionDate = new Date();
		setState(State.COMPLETED);
		//TODO destroy, audit logging, etc.
	}

	/**
	 * @see  org.workhorse.process.Process#getCreationDate()
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * @see  org.workhorse.process.Process#getCompletionDate()
	 */
	public Date getCompletionDate() {
		return completionDate;
	}

    /**
     * @see org.workhorse.process.Process#getService(Class)
     */
    public <S extends Service> S getService(Class<S> type) {
        return services.getService(type);
    }

    /**
	 * @see org.workhorse.process.Process#getExecutions()
	 */
	public Collection<Execution> getExecutions() {
		return executions;
	}

	/**
	 * @see org.workhorse.process.Process#getPausedExecutions(org.workhorse.graph.node.Node)
	 */
    @Override
	public Collection<Execution> getPausedExecutions(Node node) {
		return null;	//TODO impelement getPausedExecutions
	}

    @Override
    public Node getNode(String nodeId) {
        return null;  //TODO implement getNode()
    }
}
