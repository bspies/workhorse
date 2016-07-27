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
package org.workhorse.actor.role;


import org.workhorse.id.Identifiable;

/**
 * A {@code Role} is used to match participants in the process
 * with a corresponding set of privileges. Roles are hierarchical, so
 * they can inherit privileges from parent roles.
 *
 * @author Brennan Spies
 */
public class Role implements Identifiable<String>
{
	private String name, id;
	private Role parent;

	/**
	 * Default constructor.
	 * @param id The role id
	 */
	public Role(String id, String name) {
		this.id = id;
        this.name = name;
	}

	/**
	 * Creates a child role with it parent.
	 * @param id The role id
     * @param name The role name
	 * @param parent The parent role
     */
	public Role(String id, String name, Role parent) {
		this(id, name);
		this.parent = parent;
	}

	/**
	 * Returns the name of the role.
	 * @return The role name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the parent of this role.
	 * @return The role parent or null if none
	 */
	public Role getParent() {
		return parent;
	}

	@Override public String getId() {
		return null;
	}

	//public Set<Privilege> getPrivileges();
}
