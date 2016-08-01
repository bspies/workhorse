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

import org.workhorse.actor.User;
import org.workhorse.actor.person.Name;
import org.workhorse.actor.role.Position;

/**
 * A specialization of {@link User} to represent
 * an employee of a specific {@link Organization}
 */
public class Employee extends User {

    private Position position;

    /**
     * Default constructor.
     * @param id The user id
     * @param name The name of the user
     * @param position The employee position
     */
    public Employee(String id, Name name, Position position) {
        super(id, name);
        this.position = position;
    }

    /**
     * Returns the organization to which the employee belongs.
     * @return The employer organization
     */
    Organization belongsTo() {
        return position.getOrganization();
    }

    /**
     * Returns the employee's position.
     * @return The position
     */
    Position getPosition() {
        return position;
    }
}
