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
package org.workhorse;

/**
 * Represents an object that is describable, i.e.
 * has a name and a description.
 *
 * @author Brennan Spies
 */
public interface Describable {

    /**
     * Returns the name.
     * @return The name
     */
    String getName();

    /**
     * Sets the name.
     * @param name The name to set
     */
    void setName(String name);

    /**
     * Returns the description.
     * @return The description
     */
    String getDescription();

    /**
     * Sets the description.
     * @param description The description to set
     */
    void setDescription(String description);
}
