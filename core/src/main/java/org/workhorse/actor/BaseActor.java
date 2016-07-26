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
package org.workhorse.actor;

import org.workhorse.actor.person.Name;

import java.util.Locale;

/**
 * Base implementation of the {@link Actor} interface.
 *
 * @author Brennan Spies
 */
public class BaseActor implements Actor {

    private long id;
    private Name name;

    /**
     * Default constructor.
     * @param id The actor id
     * @param name The name of the actor
     */
    public BaseActor(long id, Name name) {
        this.id = id;
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override public Long getId() {
        return id;
    }

    /** {@inheritDoc} */
    @Override public String getDisplayName() {
        return name.getDisplay(Locale.getDefault());  //todo allow dynamic Locale?
    }
}
