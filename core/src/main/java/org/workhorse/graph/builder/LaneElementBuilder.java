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

import org.workhorse.graph.Lane;

/**
 * Interface for builders that built lane-contained
 * objects.
 *
 * @author Brennan Spies
 */
public interface LaneElementBuilder<B> {
    /**
     * Sets the lane for this builder.
     * @param lane The lane
     * @return The builder
     */
    B onLane(Lane lane);

    /**
     * Sets the lane for this builder.
     * @param laneBuilder The lane builder
     * @return The builder
     */
    B onLane(ContextualBuilder<Lane> laneBuilder);

    /**
     * Sets the lane for this builder.
     * @param laneRef The lane reference
     * @return The builder
     */
    B onLane(LaneReference laneRef);
}
