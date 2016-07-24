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
package org.workhorse.process;

import org.workhorse.id.Identifiable;

import java.util.UUID;

/**
 * Represents an identifiable element, belonging
 * to an instance of a {@link Process}.
 *
 * @author Brennan Spies
 */
public abstract class Element implements Identifiable<UUID>
{
    private UUID id;

    /**
     * Default constructor.
     * @param id The element's id
     */
    public Element(UUID id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    public UUID getId() {
        return id;
    }
}
