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
 * @author  Brennan Spies
 */
public class Cancellation extends BaseEvent implements ThrownEvent<Cancellation>
{
	public static final EventType<Cancellation> type =
			new EventType<>(new TypeLiteral<Cancellation>() {});
	private String reason;
	
	/**
	 * @param source The event source
	 * @param reason The event reason
	 */
	public Cancellation(Node source, String reason) {
		super(source);
		this.reason = reason;
	}
	
	/**
     * Gets the reason for the cancellation.
	 * @return  The reason for the cancellation
	 */
	public String getReason() { return reason; }

	/**
     * {@inheritDoc}
     */
	public EventType<Cancellation> getType() {
		return type;
	}
}
