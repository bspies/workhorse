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
package org.workhorse.event;

import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.graph.node.CatchingNode;
import org.workhorse.util.Maps;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * The {@code EventManager} manages queues for all {@link CatchingNode} events
 * as well as any {@link ThrownEvent} which is not matched.
 *
 * @author  Brennan Spies
 */
public class EventManager 
{
	private final Map<Serializable,Queue<ThrownEvent>> eventQueues;
	private final org.workhorse.process.Process process;
	
	public EventManager(org.workhorse.process.Process process) {
		this.process = process;
		eventQueues = Maps.newHashMap();
	}
	
	public <E extends ThrownEvent<E>> void notifyReceived(final CatchingNode<E> node, E event) {
		Collection<Execution> toBeContd;
		synchronized(node) {	
			toBeContd = process.getPausedExecutions(node);
			if(toBeContd.size()==0) {
				Queue<ThrownEvent> queue;
				if((queue = eventQueues.get(node.getId()))==null) {
					queue = new LinkedList<ThrownEvent>();
                }
				queue.offer(event);
			}
		}
		//resume executions, if any
		for(Execution exec : toBeContd) {
			node.getCatcher().handle(event, exec);
			exec.resume();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <E extends ThrownEvent<E>> void consumeOrPause(final CatchingNode<E> node, Execution execution) {
		synchronized(node) {
			Queue<ThrownEvent> queue = eventQueues.get(node.getId());
			E event;
			if(queue!=null && (event=(E)queue.poll())!=null) {
				node.getCatcher().handle(event, execution);
			} else {
				execution.pause(node, Phase.LEAVE);
			}
		}
	}
}
