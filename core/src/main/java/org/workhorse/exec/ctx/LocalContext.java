package org.workhorse.exec.ctx;

import org.workhorse.var.LocalName;
import org.workhorse.var.MutableVariable;

/**
 * Represents a {@link Context} that can only exist within a single
 * namespace and thus should only be accessed by a single
 * {@link org.workhorse.exec.Execution}.
 *
 * @author Brennan Spies
 */
public interface LocalContext extends Context<LocalName> {

    /**
     * Gets the variable with the given local name, or null
     * if no such variable exists in this context.
     * @param name The variable name
     * @return The variable, or null
     */
    @Override
    public MutableVariable<?> getVariable(LocalName name);

    /**
     * Gets the variable with the given local name, or null
     * if no such variable exists in this context.
     * @param simpleName The variable name
     * @return The variable, or null
     */
    public MutableVariable<?> getVariable(String simpleName);

    /**
     * Sets the existing variable, or creates a new one if none
     * is defined in the current context.
     * @param localName The variable's local name
     * @param value The value of the variable
     */
    public <T> void setVariable(LocalName localName, T value);

    /**
     * Sets the existing variable, or creates a new one if none
     * is defined in the current context.
     * @param simpleName The variable's local name
     * @param value The value of the variable
     */
    public <T> void setVariable(String simpleName, T value);
}
