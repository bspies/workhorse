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

import com.google.inject.TypeLiteral;
import org.workhorse.graph.Node;

/**
 * An event indicating that an error has occurred.
 *
 * @author Brennan Spies
 */
public class Error extends BaseEvent implements ThrownEvent<Error>
{
	public static final EventType<Error> type =
		new EventType<Error>(new TypeLiteral<Error>(){});

	private final String name;
	private String message;
	
	/**
	 * Constructs an {@code Error} with a name.
	 * @param origin The node that is the origin of the error
	 * @param errorName The error name
	 */
	public Error(Node origin, String errorName) {
		super(origin);
		this.name = errorName;
	}
	
	/**
	 * Constructs an {@code Error} with a name and message.
	 * @param origin The origin node of the error
	 * @param errorName The error name
	 * @param message The error message
	 */
	public Error(Node origin, String errorName, String message) {
		this(origin, errorName);
		this.message = message;
	}
	
	/**
	 * Returns the error name. Required for matching error catching events.
	 * @return The error name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the description of the error.
	 * @return  The error description
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	public EventType<Error> getType() {
		return type;
	}
}
