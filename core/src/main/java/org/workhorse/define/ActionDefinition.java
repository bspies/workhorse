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
package org.workhorse.define;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.workhorse.activity.Action;

/**
 * @author  Brennan Spies
 */
@XmlRootElement(name="Action")
public class ActionDefinition<T extends Action> extends ActivityDefinition
{
	/**
	 * @uml.property  name="actionClass"
	 */
	private Class<T> actionClass;

	/**
	 * Gets the <code>Action</code> class.
	 * @return
	 * @uml.property  name="actionClass"
	 */
	@XmlValue
	public Class<T> getActionClass() {
		return actionClass;
	}

	/**
	 * Sets the <code>Action</code> class.
	 * @param actionClass  The <code>Action</code> class
	 * @uml.property  name="actionClass"
	 */
	public void setActionClass(Class<T> actionClass) {
		this.actionClass = actionClass;
	}
}
