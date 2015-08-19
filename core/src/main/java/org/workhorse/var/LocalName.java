package org.workhorse.var;

/**
 * @author Brennan Spies
 */
public class LocalName implements Name
{
    private String name;
    
    //internal use only
    public LocalName() {}

    public LocalName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalName localName = (LocalName) o;
        return name.equals(localName.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override public final String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
