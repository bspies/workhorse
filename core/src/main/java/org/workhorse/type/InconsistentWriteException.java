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
package org.workhorse.type;

/**
 * Indicates that an inconsistent write has been attempted (and failed).
 *
 * @author Brennan Spies
 */
public class InconsistentWriteException extends RuntimeException {

    /**
     * Creates an exception
     * @param name The name of the variable
     * @param attemptedValue The value attempted to set (but failed)
     * @param existingValue The current value of the variable
     * @param reason The failure reason
     */
    public InconsistentWriteException(String name,
                                      Object attemptedValue,
                                      Object existingValue,
                                      String reason) {
        super(String.format(
                "Attempt to set value '%s' on variable %s has failed. Value remains '%s'. Reason: %s",
                        attemptedValue, name, existingValue, reason));
    }
}
