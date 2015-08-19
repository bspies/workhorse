package org.workhorse.exec;

/**
 * @author Brennan Spies
 */
public interface Executable {
    //

    /**
     * Returns the task name.
     * @return The task name
     */
    String getName();

    /**
     * Returns the description of the task.
     * @return The task description
     */
    String getDescription();
}
