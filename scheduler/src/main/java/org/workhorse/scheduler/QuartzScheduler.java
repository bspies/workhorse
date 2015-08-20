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
package org.workhorse.scheduler;

import java.util.Date;

import org.workhorse.exec.Execution;
import org.workhorse.persistence.Persistence;
import org.workhorse.scheduler.Scheduler;
import org.quartz.SchedulerException;
import com.google.inject.Inject;

/**
 * @author Brennan Spies
 *
 */
public class QuartzScheduler implements Scheduler 
{
    private org.quartz.Scheduler quartzScheduler;

    /**
     * Default constructor.
     * @param quartzScheduler  The Quartz Scheduler implementation
     */
    @Inject
    public QuartzScheduler(org.quartz.Scheduler quartzScheduler) {
         this.quartzScheduler = quartzScheduler;
    }

	/**
	 * @see org.workhorse.service.Service#shutdown()
	 */
	public void shutdown() {
        try {
            quartzScheduler.shutdown();
        } catch (SchedulerException e) {
            //TODO
        }
    }

	/**
	 * @see org.workhorse.service.Service#start()
	 */
	public void start() {
        try {
            quartzScheduler.start();
        } catch (SchedulerException e) {
           //TODO
        }
    }
	
	/**
	 * @see org.workhorse.scheduler.Scheduler#wakeup(org.workhorse.persistence.Persistence , org.workhorse.exec.Execution, java.util.Date)
	 */
	public void wakeup(Persistence persistenceService, Execution execution, Date wakeupTime) {
		// TODO Auto-generated method stub

	}
}
