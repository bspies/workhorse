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
package org.workhorse.util.commonj;

import java.util.concurrent.Executor;

import com.google.inject.Inject;

import commonj.work.Work;
import commonj.work.WorkManager;

/**
 * <p>Utility class for using the <code>commonj.work.WorkManger</code> as an {@link Executor},
 * specifically for building a {@link org.workhorse.flow.Controller} instance.</p>
 * 
 * <p>Note: CommonJ is a <a href="http://jcp.org/en/jsr/detail?id=237">specification</a>
 * for using application-level concurrency in Java application servers; one of its key design goals
 * is to prevent container-hosted applications from creating their own threads, since this is considered 
 * bad practice. CommonJ is currently only implemented in IBM WebSphere 6.0+ and BEA/Oracle Weblogic 9.0+.</p>
 *
 *  @author Brennan Spies
 */
public class WorkManagerExecutor implements Executor 
{
	private WorkManager workManager;
	
	@Inject
	public WorkManagerExecutor(WorkManager workManager) {
		this.workManager = workManager;
	}

	/**
	 * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
	 */
	public void execute(Runnable command) {
		workManager.schedule(new WorkAdapter(command));	
	}
	
	/*
	 * Adapter class for the CommonJ Work interface.
	 */
	private static class WorkAdapter implements Work 
	{
		private Runnable runnable;
		
		WorkAdapter(Runnable runnable) {
			this.runnable = runnable;
		}

		public boolean isDaemon() {
			return false;
		}

		public void release() {}

		public void run() {
			runnable.run();
		}
		
	}
}
