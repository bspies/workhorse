package org.workhorse.runtime.osgi;

import org.workhorse.api.AdminApi;
import org.workhorse.api.ClientApi;
import org.workhorse.runtime.Engine;

import java.io.Serializable;

/**
 * @author Brennan Spies
 */
public class ModularEngine implements Engine {

    @Override
    public void start() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void end() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AdminApi getAdminClient(Serializable credentials) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ClientApi getClient(Serializable credentials) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
