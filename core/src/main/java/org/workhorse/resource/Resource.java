package org.workhorse.resource;

import java.util.Map;

/**
 * Representation of a resource that is used by the workflow system, but is external
 * to it: e.g. a database, an LDAP server, a network file share, etc.
 *
 * @author Brennan Spies
 */
public interface Resource {
    /**
     * Returns the properties associated with this resource.
     * @return The resource properties
     */
    public Map<String,String> getProperties();

    /**
     * Returns the name of the resource.
     * @return The name of the resource
     */
    public String getName();
}
