/**
 * 
 */
package org.workhorse;

import java.util.Date;

/**
 * @author Brennan Spies
 * 
 * <p>Interface to capture common measurements for process elements.</p>
 */
public interface Recordable 
{
	/**
	 * Returns the date that the work was created.
	 * @return The task creation date
	 */
	public Date getCreationDate();
	
	/**
	 * Returns the date the work was completed.
	 * @return The task completion date
	 */
	public Date getCompletionDate();
}
