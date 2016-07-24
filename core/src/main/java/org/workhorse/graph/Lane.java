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
package org.workhorse.graph;

/**
 * Represents a swim lane in a BPMN diagram.
 *
 * @author Brennan Spies
 */
public class Lane 
{
	private String roleName;
	
	/**
	 * Constructor, takes the 
	 * @param roleName The role name
	 */
	public Lane(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * Returns the Role which is responsible for this Lane.
	 * @return The owner role
	 */
	public String getRoleName() {
		return roleName;
	}
}
