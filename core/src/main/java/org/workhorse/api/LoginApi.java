/**
 * 
 */
package org.workhorse.api;

import java.io.Serializable;

/**
 * @author Brennan Spies
 *
 */
public interface LoginApi {
	/**
	 * Logs into the (local or remote) process engine.
	 * @param credentials The user credentials
	 * @return The client API
	 */
	public ClientApi login(Serializable credentials);

    /**
     * Logs into the (local or remote) process engine as an admininistrator.
     * @param credentials The admin credentials
     * @return The admin API
     */
    public AdminApi adminLogin(Serializable credentials);
}
