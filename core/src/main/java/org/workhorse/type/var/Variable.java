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
package org.workhorse.type.var;

import org.workhorse.type.constraint.Constraint;
import org.workhorse.type.val.Value;

import java.util.Optional;

/**
 * Represents a symbol in the workflow system whose
 * value can be changed. The value that can be set
 * on a {@code Variable} may also be optionally constrained
 * using a {@link Constraint}.
 *
 * @author Brennan Spies
 */
public interface Variable<V> extends Value<V> {
    /**
     * Sets the value of the variable.
     * @param value The value to set
     * @throws org.workhorse.type.constraint.ConstraintViolation If a constraint is violated
     */
    void setValue(V value);

    /**
     * Sets the value constraint for this variable.
     * @param constraint The value constraint
     */
    void setConstraint(Constraint<V> constraint);

    /**
     * Returns the optional constraint on the
     * variable.
     * @return The optional constraint
     */
    Optional<Constraint<V>> getOptionalConstraint();
}
