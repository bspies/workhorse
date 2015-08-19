package org.workhorse.security;

import org.workhorse.security.Authentication;
import org.workhorse.actor.User;

import javax.naming.InitialContext;
import javax.naming.spi.InitialContextFactory;

/**
 * @author Brennan Spies
 *
 */
public class LdapAuthentication implements Authentication
{
    private InitialContextFactory initContextFactory;

    public User authenticate(Object credentials) {
        InitialContext initContext = null;
        try {
            
        } finally {

        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void start() {}

    public void shutdown() {}
}
