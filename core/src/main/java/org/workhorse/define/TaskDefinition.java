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

import org.workhorse.activity.Allocation;

/**
 * The task definition from a BPMN process diagram.
 *
 * @author Brennan Spies
 */
@XmlRootElement(name="Task")
public class TaskDefinition extends ActivityDefinition
{
	private Allocation allocation;

	/**
	 * @return  the allocation
	 */
	public Allocation getAllocation() {
		return allocation;
	}

	/**
	 * @param allocation  the allocation to set
	 */
	public void setAllocation(Allocation allocation) {
		this.allocation = allocation;
	}
}
