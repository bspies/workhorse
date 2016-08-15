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

import java.util.function.Function;

/**
 * A reference to an existing or future-built lane.
 *
 * @author Brennan Spies
 */
public class LaneReference {

    private Function<BuilderContext,Lane> laneProvider;

    public LaneReference(Function<BuilderContext,Lane> laneProvider) {
        this.laneProvider = laneProvider;
    }

    /**
     * Returns the lane that is referenced.
     * @param ctx The builder context
     * @return The lane
     */
    public Lane getLane(BuilderContext ctx) {
        return laneProvider.apply(ctx);
    }
}
