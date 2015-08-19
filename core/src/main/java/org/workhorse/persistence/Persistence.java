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

import org.workhorse.exec.Execution;
import org.workhorse.graph.node.Node;
import org.workhorse.process.Process;
import org.workhorse.service.Service;
import org.workhorse.Identifiable;

import java.util.Collection;

/**
 * @author Brennan Spies
 * 
 * <p>Represents a service interface for persisting or retrieving persisted workflow state.</p>
 */
public interface Persistence extends Service
{
	/**
	 * Returns all the executions which are paused at a particular
	 * node.
	 * @param process The process instance
	 * @param node The current node for the paused execution
	 * @return The paused executions
	 */
	public Collection<Execution> getPausedExecutions(Process process, Node node);
	
	/**
	 * Persists the persistence-capable object.
	 * @param persistentObject The persistence-capable object
	 */
	public void persist(Identifiable persistentObject);

    /**
     * Removes the persistent object from the datastore.
     * @param persistentObject The persistent object to remove
     */
    public void remove(Object persistentObject);

    /**
     * Returns a {@link PersistenceManager} for externally managed transactions.
     * @return The {@code WorkManager}
     */
    public PersistenceManager getPersistenceManager();

    /**
     * Performs a unit of work within a transaction.
     * @param work The unit of work
     */
    public void performWork(UnitOfWork work);
}
