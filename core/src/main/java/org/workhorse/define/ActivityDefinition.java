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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Brennan Spies
 * <p>Abstract base class for activity defintions.</p>
 */
public abstract class ActivityDefinition 
{
	private String name;
	private String description;
	
	/**
	 * @return  the description
	 * @uml.property  name="description"
	 */
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}

	/**
	 * @return  the name
	 * @uml.property  name="name"
	 */
	@XmlAttribute
	public String getName() {
		return name;
	}

	/**
	 * @param description  the description to set
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param name  the name to set
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}
}
