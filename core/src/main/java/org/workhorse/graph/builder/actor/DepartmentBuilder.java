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

import org.workhorse.actor.org.Department;
import org.workhorse.graph.builder.BuilderContext;
import org.workhorse.graph.builder.BaseBuilder;
import org.workhorse.graph.builder.ContextualBuilder;
import org.workhorse.validation.Required;

/**
 * Builder for {@link Department} instances.
 */
public class DepartmentBuilder extends BaseBuilder implements ContextualBuilder<Department> {

    @Required private String name, id;

    /**
     * Sets the department name.
     * @param name The department name
     * @return The department builder
     */
    public DepartmentBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public DepartmentBuilder withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Builds the {@code Department}.
     * @param ctx The context
     * @return The built object
     */
    @Override
    public Department build(BuilderContext ctx) {
        if(id==null) {
            id = ctx.generateIdFor(Department.class);
        }
        runValidation();
        return new Department(id, name);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Department> getBuiltType() {
        return Department.class;
    }
}
