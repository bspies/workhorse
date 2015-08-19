/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.workhorse.persistence;

import org.workhorse.Identifiable;

/**
 * @author Brennan Spies
 *
 * <p>Interface for transactional-level persistence control.</p>
 */
public interface PersistenceManager
{
    /**
     * Begins work.
     */
    public void begin();

    /**
     * Completes the work (sucessfully). Work must
     * be started with {@code begin()} prior to calling
     * this method.
     */
    public void save();

    /**
     * Undoes the work. Work must
     * be started with {@code begin()} prior to calling
     * this method.
     */
    public void undo();

    /**
     * Performs an atomic unit of work using the supplied
     * callback
     * @param work The work callback
     */
    public void perform(UnitOfWork work);

    /**
     * Makes the object persistent.
     * @param object The object to persist
     */
    public void persist(Identifiable object);

    /**
     * Makes the persistent object non-persistent.
     * @param object The object to make non-persistent
     */
    public void remove(Object object);

    /**
     * Closes the persistence manager.
     */
    public void close();
}
