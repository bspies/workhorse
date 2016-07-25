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
package org.workhorse.event;

import org.workhorse.graph.Node;
import com.google.inject.TypeLiteral;

/**
 * A compensation event.
 *
 * @author  Brennan Spies
 */
public class Compensation extends BaseEvent implements ThrownEvent<Compensation>
{
	public static final EventType<Compensation> type =
			new EventType<>(new TypeLiteral<Compensation>() {});
	
	/**
     * Constructs a compensation event with the event source.
	 * @param source The source node
	 */
	public Compensation(Node source) {
		super(source);
	}

	/**
	 * Returns the event type.
     * @return The event type
	 */
	public EventType<Compensation> getType() {
		return type;
	}
}
