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
package org.workhorse.graph.builder.actor;

import org.workhorse.actor.User;
import org.workhorse.actor.person.Name;
import org.workhorse.actor.person.WesternName;
import org.workhorse.graph.builder.BaseIdentifiableBuilder;
import org.workhorse.graph.builder.BuilderContext;
import org.workhorse.graph.builder.BaseBuilder;
import org.workhorse.validation.Required;

/**
 * Base implementation of a {@link org.workhorse.actor.User} builder.
 */
public abstract class BaseUserBuilder<U extends User,B extends BaseUserBuilder<U,B>> extends BaseIdentifiableBuilder<U,B> {

    @Required protected Name name;
    @Required protected String id;

    /**
     * Sets the user name.
     * @param name The name to set
     * @return The user builder
     */
    public B withName(Name name) {
        this.name = name;
        return self();
    }

    /**
     * Convenience method for setting the user name
     * with given and fmaily names.
     * @param firstName The given name
     * @param lastName The family name
     * @return The user builder
     */
    public B withName(String firstName, String lastName) {
        this.name = new WesternName(firstName, lastName);
        return self();
    }

    /**
     * Sets the user id.
     * @param id The user id to set
     * @return The user builder
     */
    public B withId(String id) {
        this.id = id;
        return self();
    }
}
