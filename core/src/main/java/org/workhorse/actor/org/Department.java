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
package org.workhorse.actor.org;

import org.workhorse.actor.UserGroup;

import java.util.Collection;

/**
 * Represents an employee group within an {@link Organization}.
 */
public class Department implements UserGroup<Employee> {

    private String id, name;

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the identifier
     * @return The id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the department name.
     * @return The department name
     */
    public String getDepartmentName() {
        return name;
    }

    /**
     * Returns the name of the actor.
     * @return The name
     */
    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     * Returns all of the users that belong
     * to this department.
     * @return The users
     */
    @Override
    public Collection<Employee> getUsers() {
        return null;
    }
}
