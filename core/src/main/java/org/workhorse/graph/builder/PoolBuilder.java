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

import org.workhorse.actor.Actor;
import org.workhorse.graph.Lane;
import org.workhorse.graph.Pool;
import org.workhorse.validation.Required;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Builder for participant pools.
 *
 * @author Brennan Spies
 */
public class PoolBuilder extends BaseBuilder implements ContextualBuilder<Pool> {

    @Required private ContextualBuilder<? extends Actor> participant;
    @Required private Collection<ContextualBuilder<Lane>> lanes = new ArrayList<>();
    private boolean main;

    /**
     * Sets the participant for the pool.
     * @param actor The participant to set
     * @return The pool builder
     */
    public PoolBuilder withParticipant(Actor actor) {
        return withParticipant(ExistingObject.wrap(actor));
    }

    /**
     * Sets the participant for the pool.
     * @param actorBuilder The builder for the participant
     * @return The pool builder
     */
    public PoolBuilder withParticipant(ContextualBuilder<? extends Actor> actorBuilder) {
        participant = actorBuilder;
        return this;
    }

    /**
     * Indicates whether or not this is the main pool.
     * @param isMain True if main, false if not
     * @return The pool builder
     */
    public PoolBuilder withMain(boolean isMain) {
        this.main = isMain;
        return this;
    }

    /**
     * Adds a swim lane to the pool.
     * @param laneBuilder The builder for the lane
     * @return The pool builder
     */
    public PoolBuilder withLane(ContextualBuilder<Lane> laneBuilder) {
        lanes.add(laneBuilder);
        return this;
    }

    /**
     * Adds a swim lane to the pool.
     * @param lane The lane to add
     * @return The pool builder
     */
    public PoolBuilder withLane(Lane lane) {
        return withLane(ExistingObject.wrap(lane));
    }

    /** {@inheritDoc} */
    @Override public Pool build(BuilderContext ctx) {
        runValidation();
        Pool pool = new Pool(ctx.build(participant));
        pool.setMain(main);
        lanes.forEach(lane -> pool.addLane(ctx.build(lane)));
        return pool;
    }

    /** {@inheritDoc} */
    @Override
    public Class<Pool> getBuiltType() {
        return Pool.class;
    }
}
