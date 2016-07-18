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
package org.workhorse.event.handler;

import org.workhorse.event.Error;
import org.workhorse.event.EventType;
import org.workhorse.exec.Execution;

/**
 * {@code Catcher} for {@code Error} events. Can match specific named errors,
 * or all errors (if no name specified).
 *
 * @author Brennan Spies
 */
public class ErrorCatcher implements Catcher<Error> 
{	
	private String name;
	
	/**
	 * Constucts an {@code ErrorCatcher} that matches the named
	 * error.
	 * @param name The error name
	 */
	public ErrorCatcher(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the error name that this {@code ErrorCatcher} catches.
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void handle(Error error, Execution execution) {
		//TODO implement
	}

	/**
     * {@inheritDoc}
     */
	public EventType<Error> forType() {
		return Error.type;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean matches(Error event) {
		return name==null || name.equals(event.getName());
	}
}
