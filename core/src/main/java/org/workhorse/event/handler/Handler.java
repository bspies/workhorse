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
package org.workhorse.event.handler;

import org.workhorse.event.Event;
import org.workhorse.exec.Execution;

/**
 * Interface for all classes that handle events.
 *
 * @author Brennan Spies
 */
public interface Handler<E extends Event> 
{
	/**
	 * Method called when an event is received by an {@link Execution}.
	 * @param event The event
	 * @param execution The execution processing the event
	 */
	public void handle(E event, Execution execution);
}
