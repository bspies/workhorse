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

import org.workhorse.graph.node.Node;

/**
 * A timer event that redirects flow after the set time expires.
 *
 * @author Brennan Spies
 */
public class Timeout extends BaseEvent
{
	private long timeExpired;

    /**
     * Creates a timeout with the source node and the time
     * until expiration.
     * @param source The source node
     * @param timeExpired The time until expiration, in milliseconds
     */
	public Timeout(Node source, long timeExpired) {
		super(source);
		this.timeExpired = timeExpired;
	}
	
	/**
     * Returns the time to expiration.
	 * @return The time in milliseconds until expiration
	 */
	public long getTimeExpired() {
		return timeExpired;
	}
}
