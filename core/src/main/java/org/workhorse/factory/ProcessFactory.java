/**
 * 
 */
package org.workhorse.factory;

import org.workhorse.define.ProcessDefinition;
import org.workhorse.process.Process;

/**
 * @author Brennan Spies
 *
 */
public interface ProcessFactory 
{
	/**
	 * Creates a new process.
	 * @param diagram The process diagram
	 * @return The process instance
	 */
	public Process createProcess(ProcessDefinition diagram);
}
