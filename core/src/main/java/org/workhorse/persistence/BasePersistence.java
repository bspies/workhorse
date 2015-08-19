package org.workhorse.persistence;

import com.google.inject.Provider;
import org.workhorse.Identifiable;

/**
 *  @author Brennan Spies
 *
 * <p></p>
 */
public abstract class BasePersistence implements Persistence
{
    private static ThreadLocal<PersistenceManager> pmBinding;
    private Provider<PersistenceManager> pmProvider;

    protected void init(final Provider<PersistenceManager> pmProvider) {
        this.pmProvider = pmProvider;
        pmBinding = new ThreadLocal<PersistenceManager> () {
            @Override
            protected PersistenceManager initialValue() {
                return pmProvider.get();
            }
        };
    }

    /**
     * Makes a persistence-capable object persistent.
     * @param persistentObject The persistence-capable object
     */
    public void persist(Identifiable persistentObject) {
        getPersistenceManager().persist(persistentObject);
    }

    /**
     * Removes the persistent object from the datastore.
     * @param persistentObject The persistent object to remove
     */
    public void remove(Object persistentObject) {
        getPersistenceManager().remove(persistentObject);
    }

    /**
     * Performs a transactional unit of work.
     * @param work The unit of work
     */
    public void performWork(UnitOfWork work) {
        getPersistenceManager().perform(work);
    }

    /**
     * Returns the persistence manager.
     * @return The persistence manager
     */
    public PersistenceManager getPersistenceManager() {
        PersistenceManager pm = pmBinding.get();
        if(pm==null) {
            pm = pmProvider.get();
            pmBinding.set(pm);
        }
        return pm;
    }

    /**
     * Closes the {@link PersistenceManager}.
     */
    protected void closePersistenceManager() {
        PersistenceManager pm = pmBinding.get();
        if(pm!=null) {
            pm.close();
            pmBinding.remove();
        }
    }
}
