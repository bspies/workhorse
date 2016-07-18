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
package org.workhorse.type.var;

import org.workhorse.type.constraint.Constraint;
import org.workhorse.type.constraint.ConstraintViolation;
import org.workhorse.type.val.BaseValue;

import java.util.Optional;

/**
 * Base implementation of {@link Variable}.
 */
abstract class BaseVariable<V> extends BaseValue<V> implements Variable<V> {

    private Constraint<V> constraint;

    BaseVariable(String name, Class<V> type) {
        super(name, type);
    }

    /**
     * Sets the value constraint for this variable.
     * @param constraint The value constraint
     */
    @Override
    public void setConstraint(Constraint<V> constraint) {
        this.constraint = constraint;
    }

    /** {@inheritDoc */
    @Override public Optional<Constraint<V>> getOptionalConstraint() {
        return Optional.ofNullable(constraint);
    }

    /**
     * Performs validation of a potential variable value, throwing
     * an exception if there is a constraint violation.
     * @param valueToSet The potential value
     * @throws ConstraintViolation If the {@code valueToSet} violates the constraint
     */
    void validate(V valueToSet) {
        Optional<Constraint<V>> c = getOptionalConstraint();
        if(c.isPresent() && !c.get().isValid(valueToSet)) {
            throw new ConstraintViolation(c.get(), valueToSet);
        }
    }
}
