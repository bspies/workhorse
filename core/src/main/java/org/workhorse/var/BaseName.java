package org.workhorse.var;

import javax.annotation.Nullable;

/**
 * @author Brennan Spies
 */
public abstract class BaseName implements Name
{
    private String name, qualifier;

    protected BaseName() {}

    protected BaseName(@Nullable String qualifier, String name) {
       this.qualifier = qualifier;
       this.name = name;
    }

    protected String getQualifier() {
        return qualifier;
    }
}
