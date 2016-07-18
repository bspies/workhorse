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
package org.workhorse.type.constraint;

/**
 * Exception thrown when a {@link Constraint}
 * is violated.
 *
 * @author Brennan Spies
 */
public class ConstraintViolation extends RuntimeException {

    /**
     * Default constructor.
     * @param constraint The constraint that was violated
     * @param value The value that violated the constraint
     */
    public ConstraintViolation(Constraint<?> constraint, Object value) {
        super(String.format("Constraint '%s' violated, attempted value: %s", constraint, value));
    }
}
