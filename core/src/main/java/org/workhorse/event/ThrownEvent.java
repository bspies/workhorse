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

/**
 * Interface for events that are fired by a {@link Trigger}
 * and may be caught by a {@link org.workhorse.event.handler.Handler Handler}.
 *
 *  @author Brennan Spies
 */
public interface ThrownEvent<E extends ThrownEvent<E>> extends Event
{
	/**
	 * The event type.
	 * @return The event type
	 */
	EventType<E> getType();
}
