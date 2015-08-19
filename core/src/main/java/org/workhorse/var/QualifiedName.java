package org.workhorse.var;

/**
 * Represents a fully-qualified {@link Variable} localName.
 *
 * @author Brennan Spies
 */
public class QualifiedName implements Name
{
    private String namespace, localName;
    
    //internal use only
    public QualifiedName() {}

    /**
     * Creates a variable localName with the namespace and
     * local localName.
     * @param namespace The namespace
     * @param localName The local localName
     */
    public QualifiedName(String namespace, String localName) {
        this.namespace = namespace;
        this.localName = localName;
    }

    /**
     * Creates a variable localName with the namespace and local localName.
     * @param namespace The namespace
     * @param localName The local localName
     */
    public QualifiedName(String namespace, LocalName localName) {
        this(namespace, localName.getName());
    }

    /**
     * Returns the namespace of the variable localName.
     * @return The namespace
     */
    public final String getNamespace() {
        return namespace;
    }                                        

    /**
     * Returns the local localName (sans namespace) of the variable.
     * @return The local localName
     */
    public final String getLocalName() {
        return localName;
    }

    /**
     * {@inheritDoc}
     */
    @Override public final String getName() {
        return String.format("%s:%s", namespace, localName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QualifiedName that = (QualifiedName) o;

        return localName.equals(that.localName) &&
                !(namespace != null ? !namespace.equals(that.namespace) :
                    that.namespace != null);
    }

    @Override
    public int hashCode() {
        int result = namespace != null ? namespace.hashCode() : 0;
        result = 31 * result + localName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getName();
    }
}
