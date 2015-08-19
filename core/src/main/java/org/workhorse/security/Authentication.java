/**
 * 
 */
package org.workhorse.security;

import org.workhorse.actor.User;
import org.workhorse.service.Service;

/**
 * @author Brennan Spies
 *
 * <p>Service for verifying/accessing user and group identity.</p>
 */
public interface Authentication extends Service
{
	/**
     * Authenticates user credentials
     * @param credentials The presented credentials
	 * @return The workflow user
	 */
	public User authenticate(Object credentials);
}
