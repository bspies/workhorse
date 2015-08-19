package org.workhorse.runtime.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.ops4j.peaberry.Peaberry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.workhorse.config.CoreModule;
import org.workhorse.runtime.Engine;

/**
 * The bundle activator for the core WorkHorse bundle, which bootstraps the workflow
 * engine.
 *
 * @author Brennan Spies
 */
public class CoreActivator implements BundleActivator
{
    private Engine engine;

    /**
     * Bootstraps the WorkHorse engine, registring services and server connectors.
     * @param bundleContext The bundle context
     * @throws Exception If an error is thrown
     */
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        Injector injector = Guice.createInjector(Peaberry.osgiModule(bundleContext),
                new CoreModule(), new AbstractModule() {
                    protected void configure() {
                        bind(Engine.class).to(ModularEngine.class);
                    }
                }
        );
        engine = injector.getInstance(Engine.class);
        engine.start();
    }

    /**
     * Stops the workflow engine.
     * @param bundleContext The bundle context
     * @throws Exception
     */
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        engine.end();
    }
}
