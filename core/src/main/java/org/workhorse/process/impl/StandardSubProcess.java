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
package org.workhorse.process.impl;

import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.Execution;
import org.workhorse.factory.ActivityFactory;
import org.workhorse.process.SubProcess;

/**
 * @author Brennan Spies
 *
 * <p>The standard implmentation of a <code>SubProcess</code>.</p>
 */
public class StandardSubProcess extends BaseContainer implements SubProcess
{
	private boolean transactional;
	private String name, description;

    //For JPA only
    public StandardSubProcess() {}
	
	/**
     * @param name The subprocess name
	 * @param factory The activity factory
     * @param context The context for the subprocess
	 */
	public StandardSubProcess(String name, ActivityFactory factory, Context context) {
		super(factory, context);
		this.name = name;
	}
	
	/**
	 * @see org.workhorse.activity.Activity#getDescription()
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the subprocess.
	 * @param description The subprocess description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see org.workhorse.activity.Activity#getName()
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @see org.workhorse.process.SubProcess#isTransactional()
	 */
	public boolean isTransactional() {
		return transactional;
	}

	/**
	 * @see org.workhorse.process.SubProcess#start(org.workhorse.exec.Execution)
	 */
	public void start(Execution execution) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.workhorse.process.Container#end()
	 */
	public void end() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.workhorse.process.Container#cancel()
	 */
	public void cancel() {
		// TODO Auto-generated method stub
		
	}
}
