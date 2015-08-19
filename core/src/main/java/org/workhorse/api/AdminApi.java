package org.workhorse.api;

import java.util.Collection;

import org.workhorse.define.ProcessDefinition;

/**
 * @author Brennan Spies
 * <p>Administrative interface for a WorkHorse {@link org.workhorse.runtime.Engine Engine}</p>
 */
public interface AdminApi 
{	
	/**
	 * Returns the currently active processes in the {@code Engine}
	 * @return The active processes.
	 */
	public Collection<ProcessApi> getActiveProcesses();
	
	/**
	 * Creates a process, and returns the client to it.
	 * @param definition The process definition
	 * @return The process proxy
	 */
	public ProcessApi createProcess(ProcessDefinition definition);
}
