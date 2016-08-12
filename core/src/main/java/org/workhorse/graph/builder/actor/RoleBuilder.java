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

import org.workhorse.actor.role.Role;
import org.workhorse.graph.builder.BaseIdentifiableBuilder;
import org.workhorse.graph.builder.BuilderContext;
import org.workhorse.graph.builder.ContextualBuilder;
import org.workhorse.validation.Required;

/**
 * Builder for {@link Role} instances.
 *
 * @author Brennan Spies
 */
public class RoleBuilder extends BaseIdentifiableBuilder<Role,RoleBuilder> {

    @Required private String roleName, id;

    public RoleBuilder(String roleName) {
        this.roleName = roleName;
    }

    /** {@inheritDoc} */
    @Override
    public Role build(BuilderContext ctx) {
        setIdIfAbsent(ctx);
        return new Role(id, roleName);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Role> getBuiltType() {
        return Role.class;
    }
}
