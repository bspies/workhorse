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

import org.workhorse.Cancellable;
import org.workhorse.Stoppable;
import org.workhorse.activity.Activity;

/**
 * Represents a sub-process within a top-level process.
 *
 * @author Brennan Spies
 */
public interface SubProcess extends Container, Activity, Cancellable {

    /**
     * Finishes the {@code SubProcess} instance
     * via "normal" termination.
     */
    public void end();

    /**
     * Flag to indicate if a subprocess represents a transactional
     * boundary.
     * @return True if subprocess represents a transaction
     */
    boolean isTransactional();
}
