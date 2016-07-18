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
 * Represents a timed wait event.
 *
 * @author Brennan Spies
 */
public class TimedWait extends BaseEvent
{
	private long interval;

	/**
	 * Creates the timed wait event.
	 * @param source The source node
	 * @param interval The interval of the wait
     */
	public TimedWait(Node source, long interval) {
		super(source);
		this.interval = interval;
	}
	
	/**
	 * Returns the wait interval.
	 * @return The wait interval
	 */
	public long getInterval() { return interval; }
}
