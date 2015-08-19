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

import java.io.Serializable;
import java.util.Map;

/**
 * A signal event, as defined by the BPMN specification.
 *
 * @author Brennan Spies
 */
public interface Signal extends ThrownEvent<Signal>
{
	/**
	 * Returns the properties associated with this
	 * signal event.
	 * @return The properties
	 */
	public Map<String,Serializable> getProperties();
}