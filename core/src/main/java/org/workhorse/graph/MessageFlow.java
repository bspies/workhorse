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
package org.workhorse.graph;

import org.workhorse.event.Message;
import org.workhorse.graph.event.CatchingNode;

/**
 * Represents a message flow.
 *
 * @author  Brennan Spies
 */
public class MessageFlow<M> implements Connector<Node,Node>
{
	private CatchingNode<Message<M>> target;
	private Node source;

	/**
	 * Returns the source of the message.
	 * @return The source
	 */
	@Override public Node getSource() {
		return null;
	}

	/**
	 * Returns the receiver that is the target of the message flow.
	 * @return  The message receiver
	 */
	public CatchingNode<Message<M>> getTarget() {
		return target;
	}
	
	/**
	 * Sends a message to the receiver.
	 * @param message The messasge to send
	 */
	public void send(Message<M> message) {
		//target.receiveEvent(message);
	}
}
