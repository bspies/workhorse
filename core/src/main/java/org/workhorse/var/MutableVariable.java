package org.workhorse.var;

import org.workhorse.var.constraint.Constraint;

import javax.annotation.Nullable;

/**
 * @author Brennan Spies
 */
public interface MutableVariable<T> extends Variable<T> {
    /**
     * Sets the variable's value
     * @param value The value to set
     */
    void setValue(T value);

    /**
     * Returns the variable constraint, if any.
     * @return The constraint, or null if none
     */
    @Nullable
    Constraint<T> getConstraint();
}
