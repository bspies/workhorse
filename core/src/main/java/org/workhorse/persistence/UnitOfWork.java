package org.workhorse.persistence;

/**
 * @author Brennan Spies
 *
 * <p>Callback interface to represent an atomic a "unit of work".</p>
 */
public interface UnitOfWork {
    /**
     * Performs a unit of work atomically.
     * @throws Exception If an error occurs performing the work
     */
    public void doAtomically() throws Exception;
}
