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
package org.workhorse.graph.builder;

import org.workhorse.actor.role.Role;
import org.workhorse.graph.Lane;
import org.workhorse.validation.Required;

/**
 * Builder for creating {@link Lane} instances.
 *
 * @author Brennan Spies
 */
public class LaneBuilder extends BaseBuilder implements ContextualBuilder<Lane> {

    private @Required ContextualBuilder<Role> roleBuilder;

    /**
     * Creates a lane builder with a role.
     * @param role The lane role
     */
    public LaneBuilder(Role role) {
        this.roleBuilder = ExistingObject.wrap(role);
    }

    /**
     * Creates a lane builder with a role builder.
     * @param roleBuilder The role builder
     */
    public LaneBuilder(ContextualBuilder<Role> roleBuilder) {
        this.roleBuilder = roleBuilder;
    }

    /**
     * Sets the pool to which this lane belongs.
     * @param poolBuilder The pool builder
     * @return The 'this' builder
     */
    public LaneBuilder on(PoolBuilder poolBuilder) {
        poolBuilder.withLane(this);
        return this;
    }

    /** Builds the lane. */
    @Override public Lane build(BuilderContext ctx) {
        runValidation();
        return new Lane(roleBuilder.build(ctx));
    }

    /** {@inheritDoc} */
    @Override
    public Class<Lane> getBuiltType() {
        return Lane.class;
    }
}
