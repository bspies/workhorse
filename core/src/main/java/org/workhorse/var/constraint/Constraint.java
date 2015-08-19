package org.workhorse.var.constraint;

/**
 * Interface for expressing variable value constraints.
 *
 * @author Brennan Spies
 */
public interface Constraint<T> {
    /**
     * Is the given value valid by this constraint?
     * @param value The value to check
     * @return True if value meets constraint
     */
    public boolean isValid(T value);
}
