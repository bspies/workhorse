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


import org.workhorse.process.Element;

import java.util.UUID;

/**
 * A {@code Role} is used to match participants in the process
 * with a corresponding set of privileges. Roles are hierarchical, so
 * they can inherit privileges from parent roles.
 *
 * @author Brennan Spies
 */
public class Role extends Element
{
	private String name;
	private Role parent;

	/**
	 * Default constructor.
	 * @param id The element's id
	 */
	public Role(UUID id) {
		super(id);
	}

	/**
	 * Creates a child role with it parent.
	 * @param id The role id
	 * @param parent The parent role
     */
	public Role(UUID id, Role parent) {
		this(id);
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
	
	//public Set<Privilege> getPrivileges();
}
