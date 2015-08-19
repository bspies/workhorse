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
package org.workhorse.graph;

import org.workhorse.event.Message;
import org.workhorse.graph.node.CatchingNode;

/**
 * @author  Brennan Spies
 */
public class MessageFlow<M> 
{
	private CatchingNode<Message<M>> target;
	
	/**
	 * Returns the receiver that is the target of the message flow.
	 * @return  The message receiver
	 * @uml.property  name="target"
	 */
	public CatchingNode<Message<M>> getTarget() {
		return target;
	}
	
	/**
	 * Sends a message to the receiver.
	 * @param message The messasge to send
	 */
	public void send(Message<M> message) {
		target.receiveEvent(message);
	}
}
