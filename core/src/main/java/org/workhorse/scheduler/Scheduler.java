/**
 * 
 */
package org.workhorse.scheduler;

import java.util.Date;

import org.workhorse.exec.Execution;
import org.workhorse.persistence.Persistence;
import org.workhorse.service.Service;

/**
 * @author Brennan Spies
 *
 */
public interface Scheduler extends Service
{
	/**
	 * Wakes up (resumes) the given execution at the wake up time.
	 * @param persistenceService The persistence service
	 * @param execution The execution
	 * @param wakeupTime The wake up time
	 */
	public void wakeup(Persistence persistenceService, Execution execution, Date wakeupTime);
}
