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
package org.workhorse.event.handler;

import com.google.inject.TypeLiteral;
import org.workhorse.event.EventType;
import org.workhorse.event.Signal;
import org.workhorse.exec.Execution;

/**
 * The {@code Receiver} is an event handler for a {@link Signal} event.
 *
 * @author Brennan Spies
 */
public class Receiver implements Catcher<Signal>
{
	private static final EventType<Signal> type =
		new EventType<Signal>(new TypeLiteral<Signal>(){});
	
	/**
	 * {@inheritDoc}
	 */
	public void handle(Signal signal, Execution execution) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	public EventType<Signal> forType() {
		return type;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean matches(Signal event) {
		return event.getType().equals(forType());
	}
}
