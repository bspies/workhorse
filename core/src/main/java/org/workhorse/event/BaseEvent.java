/*
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

import org.workhorse.graph.Node;

/**
 * Abstract base class for {@link Event} implementations.
 *
 * @author Brennan Spies
 */
public abstract class BaseEvent implements Event
{
	private Node source;
	
	/**
	 * Creates the event with a reference to its source.
	 * @param source The event's source node
	 */
	public BaseEvent(Node source) {
		this.source = source;
	}

	/** {@inheritDoc} */
	@Override public Node getSource() {
		return source;
	}
}
