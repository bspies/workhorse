package org.workhorse.exec.impl;

import org.workhorse.Element;
import org.workhorse.exec.Executable;

/**
 * @author Brennan Spies
 */
public abstract class BaseExecutable extends Element implements Executable {

    private String name, description;

    //internal only
    public BaseExecutable() {}

    protected BaseExecutable(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override public String getName() {
        return name;
    }

    @Override public String getDescription() {
        return description;
    }
}
