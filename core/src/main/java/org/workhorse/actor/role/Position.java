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

import org.workhorse.actor.org.Organization;
import org.workhorse.actor.User;

/**
 * Represents the position of a {@link User} in the organizational
 * hierarchy.
 *
 * @author Brennan Spies
 */
public class Position
{
    private String title;
    private Position supervisor;
    private Organization organization;

	/**
	 * Creates a position with the title and organization.
	 * @param title The position title
	 * @param organization The organization
     */
    public Position(String title, Organization organization) {
        this.title = title;
        this.organization = organization;
    }

	/**
	 * Returns the organization to which the position belongs.
	 * @return The organization
	 */
	public Organization getOrganization() {
       return organization;
    }
	
	/**
	 * Returns the title of this position.
	 * @return The position title
	 */
	public String getTitle() {
       return title;
    }
	
	/**
	 * Returns the position immediately above this
	 * position in the organizational hierarchy, or null
	 * if none.
	 * @return The supervisor position
	 */
	public Position getSupervisor() {
        return supervisor;
    }

    /**
     * Sets the supervisor for this position.
     * @param supervisor The supervisor position
     */
    public void setSupervisor(Position supervisor) {
        this.supervisor = supervisor;
    }
}
