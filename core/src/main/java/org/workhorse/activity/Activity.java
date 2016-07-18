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
package org.workhorse.activity;

import org.workhorse.Describable;
import org.workhorse.exec.Contextual;
import org.workhorse.exec.Execution;
import org.workhorse.exec.RunState;
import org.workhorse.exec.ctx.ReadValues;
import org.workhorse.id.Identifiable;

import java.util.UUID;

/**
 * Base interface for all process activities.
 *
 * @author Brennan Spies
 */
public interface Activity extends Identifiable<UUID>, Contextual, Describable {

    /**
     * Performs the activity.
     * @param execution The current execution
     */
    RunState perform(Execution execution);

    /**
     * Gets the snapshot values as they were read from
     * the parent context (at the time of input set
     * evaluation).
     * @return The read values
     */
    ReadValues getReadParentValues();
}
