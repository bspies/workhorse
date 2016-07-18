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
package org.workhorse.process;

import org.workhorse.Describable;
import org.workhorse.activity.Activity;
import org.workhorse.graph.exec.ActivityNode;
import org.workhorse.id.Identifiable;
import org.workhorse.exec.Contextual;

import java.util.UUID;

/**
 * Represents an instance of any container of {@link org.workhorse.activity.Activity}
 * elements.
 *
 * @author Brennan Spies
 */
public interface Container extends Contextual, Identifiable<UUID>, Describable {
    /**
     * Returns the current state of this container.
     * @return The current state
     */
    State getState();

    /**
     * Returns true if the executable instance is active.
     * @return True if active, false otherwise
     */
    boolean isActive();

    /**
     * Evaluates the output of an activity.
     * @param activity The activity
     * @param activityNode The activity node
     */
    <T extends Activity> void evaluateOutput(T activity, ActivityNode<T> activityNode);
}
