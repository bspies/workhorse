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

import org.workhorse.actor.Actor;
import org.workhorse.actor.UserGroup;

import java.util.Collection;

/**
 * Represents an organizational unit, comprised of one
 * or more groups of users.
 *
 * @author Brennan Spies
 */
public class Organization implements Actor
{
    private Collection<UserGroup> groups;
    private String name;
    private String id;

    /**
     * Default constructor.
     *
     * @param id   The organization id
     * @param name The name of the actor
     */
    public Organization(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
	 * Returns the user groups that comprise the
	 * organization.
	 * @return The user groups
	 */
	public Collection<UserGroup> getGroups() {
        return groups;
    }

    @Override public String getDisplayName() {
        return name;
    }

    @Override public String getId() {
        return id;
    }
}
