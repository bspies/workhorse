/*
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
package org.workhorse.graph;

import org.workhorse.id.Identifiable;

/**
 * Represents an identifiable element in the {@link Diagram}.
 *
 * @author Brennan Spies
 */
public class DiagramElement implements Identifiable<String> {

    private String id;

    /**
     * Default constructor, takes the id of the
     * element.
     * @param id The id
     */
    public DiagramElement(String id) {
        this.id = id;
    }

    /**
     * Returns the id of the element.
     * @return The id
     */
    @Override public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiagramElement that = (DiagramElement) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
