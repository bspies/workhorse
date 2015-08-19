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
package org.workhorse.event.thrower;

import org.workhorse.event.Error;
import org.workhorse.event.Trigger;
import org.workhorse.event.handler.Handler;
import org.workhorse.exec.Execution;
import org.workhorse.graph.Graph;
import org.workhorse.graph.node.ActivityNode;
import org.workhorse.graph.node.Node;

/**
 * Thrower of {@link Error} events.
 *
 * @author  Brennan Spies
 */
public class ErrorThrower implements Thrower<Error> 
{
	private Trigger<Error> trigger;
	
	/**
	 * Constructs an <code>ErrorThrower</code> with the given
	 * trigger.
	 * @param trigger The error trigger
	 */
	public ErrorThrower(Trigger<Error> trigger) {
		this.trigger = trigger;
	}
	
	/**
	 * @see org.workhorse.event.thrower.Thrower#throwEvent(org.workhorse.exec.Execution)
	 */
	public void throwEvent(Execution execution) {
		Error error = trigger.generate(execution.getExecutionContext());
		Node node = error.getSource();
		//traverse parents in graph
		Graph g;
		while((g=node.getParent()) instanceof ActivityNode) {
			if(catchError(error, execution, (ActivityNode)g))
				return;
		}
		//graph must now be process
		//TODO handle errors at process level
	}
	
	//returns true if error handled (consumed)
	private boolean catchError(Error error, Execution exec, ActivityNode node) {
		Handler<Error> h;
		if((h=node.getCatcher(error))!=null) {
			h.handle(error, exec);
			return true;
		}
		return false;
	}
}
