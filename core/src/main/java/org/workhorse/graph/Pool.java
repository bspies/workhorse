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
package org.workhorse.graph;

import org.workhorse.activity.Activity;
import org.workhorse.actor.Actor;

import java.util.Collection;


/**
 * A {@code Pool} represents a participant in the process.
 *
 * @author Brennan Spies
 */
public class Pool
{
	private boolean main;
    private Collection<Lane> lanes;
    private Actor participant;

	/**
	 * Returns true if this is the main pool in the process.
	 * @return True if this is main pool
	 */
	boolean isMain() {
		return main;
	}

    /**
     * Sets the main flag to indicate whether or not
     * this pool is the main one.
     * @param isMainPool True if this pool is main
     */
	void setMain(boolean isMainPool) {
	    this.main = isMainPool;
    }
	
	/**
	 * Returns the lanes contained by this pool (must be at least one).
	 * @return The swim lanes
	 */
	Collection<Lane> getLanes() {
	    return lanes;
    }
	
	/**
	 * Returns the <code>Actor</code> that represents the pool's participant.
	 * @return The participant
	 */
	Actor getParticipant() {
	    return participant;
    }
}
