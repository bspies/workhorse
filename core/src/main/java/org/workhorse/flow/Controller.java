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
package org.workhorse.flow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executor;

import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.expr.Condition;
import org.workhorse.graph.SequenceFlow;
import org.workhorse.graph.node.StartingNode;
import org.workhorse.graph.node.Node;
import com.google.inject.Inject;

/**
 * Class responsible for diverging and converging flow control.
 *
 * @author Brennan Spies
 */
public class Controller 
{
	private final Executor executor;
    private NewThreadListener listener;

    @Inject
	public Controller(Executor executor, NewThreadListener listener) {
		this.executor = executor;
		this.listener = listener;
	}

    //fires listener for new thread
    private void notifyNewThread(Execution execution) {
       if(listener != null)
           listener.onNewThread(execution);
    }

    /**
     * Sets a new thread listener on the controller.
     * @param listener The new thread listener
     */
    @Inject
    public void setListener(NewThreadListener listener) {
        this.listener = listener;
    }
	
	/**
	 * Starts the given execution in the starting node.
	 * @param execution The execution starting
	 * @param startingNode The starting node
	 */
	public void start(final Execution execution, final StartingNode startingNode) {
		executor.execute(new Runnable() {
			public void run() {
                notifyNewThread(execution);
				startingNode.start(execution);
			}
		});
	}

    /**
     * Re-enters the node in the given phase.
     * @param execution The execution that is re-entering
     * @param entryNode The entry point node
     * @param phase The phase in the node cycle
     */
    public void reenter(final Execution execution, final Node entryNode, final Phase phase) {
        executor.execute(new Runnable() {
            public void run() {
                notifyNewThread(execution);
                entryNode.reenter(execution, phase);
            }
        });
    }
	
	/**
	 * Takes the given execution over the outgoing transitions, splitting them into
	 * child executions if there is more than one outgoing transition. If outgoing.size()==0, 
	 * this method is a no-op.
	 * @param execution The execution to be sent over the outgoing transitions
	 * @param outgoing The outgoing transitions
	 */
	public void take(final Execution execution, final Collection<SequenceFlow> outgoing) {
		switch(outgoing.size()) {
			case 0: break;
			case 1: outgoing.iterator().next().fire(execution); break;
			default:
				for(final SequenceFlow transition : outgoing) {
					executor.execute(new Runnable() {
						public void run() {
                            notifyNewThread(execution);
							transition.fire(execution.createChild());
						}
					});
				}
		}
	}
	
	/**
	 * "Joins" all non-continuing executions by performing any necessary merge
	 * logic and ending the execution.
	 * @param nonContinuing
	 */
	public void join(Collection<Execution> nonContinuing) {
		for(Execution exec : nonContinuing) {
			exec.end();
		}
	}
	
	/**
	 * Selects executions to be continued (all others will be joined) using the given
	 * condition as a filter criteria.
	 * @param executions The executions to be evaluated
	 * @param criteria The filter criteria
	 * @return The executions to continue
	 */
	public Collection<Execution> select(Collection<Execution> executions, Condition criteria)
	{
		Collection<Execution> selected = new ArrayList<Execution>();
		for(Execution exec : executions) {
			if(criteria.evaluate(exec.getExecutionContext()))
				selected.add(exec);
			else
				exec.end();
		}
		return selected;
	}
}
