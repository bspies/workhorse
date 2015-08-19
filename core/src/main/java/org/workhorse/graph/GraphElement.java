package org.workhorse.graph;

import org.workhorse.Identifiable;

/**
 * Base class for {@link ProcessDiagram} elements.
 * @author Brennan Spies
 */
public abstract class GraphElement implements Identifiable<String>
{
    private String id;

    @Override
    public String getId() {
        return id;
    }
}
