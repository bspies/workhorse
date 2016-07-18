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

/**
 * An event type, implemented as a wrapper around a super type
 * token.
 *
 * @author Brennan Spies
 */
public class EventType<E extends ThrownEvent<E>> 
{
	private TypeLiteral<E> type;
	
	/**
	 * Constructor for simple event types, i.e., where there
	 * is a one-to-one correspondence between the class of
	 * the {@link Event} and the {@code EventType}.
	 * @param type The type literal
	 */
	public EventType(TypeLiteral<E> type) {this.type = type;}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return type.hashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventType other = (EventType) obj;
		return type.equals(other.type);
	}
	
	
}
