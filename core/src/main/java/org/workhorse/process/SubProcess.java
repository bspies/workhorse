/**
 * 
 */
package org.workhorse.process;

import org.workhorse.activity.Activity;
import org.workhorse.exec.Execution;

/**
 * @author Brennan Spies
 * 
 * <p>Represents a sub-process within a top-level process.</p>
 */
public interface SubProcess extends Container, Activity
{
	/**
	 * Starts the subprocess with the given execution.
	 * @param execution The execution starting the subprocess
	 */
	public void start(Execution execution);
	
	/**
	 * Flag to indicate if a subprocess represents a transactional
	 * boundary.
	 * @return True if subprocess represents a transaction
	 */
	public boolean isTransactional();
}
