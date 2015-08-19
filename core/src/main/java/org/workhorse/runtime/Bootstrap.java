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
package org.workhorse.runtime;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.workhorse.config.CoreModule;

/**
 * Class for starting the WorkHorse engine in a standalone, non-OSGi
 * environment.
 *
 * @author Brennan Spies
 */
public class Bootstrap {

    private Engine engine;

    /**
     * Starts the workflow {@link Engine engine}.
     */
     public void start() {
        Injector injector = Guice.createInjector(new CoreModule(), new AbstractModule() {
            @Override protected void configure() {
                bind(Engine.class).to(StandardEngine.class);
            }
        });
        engine = injector.getInstance(Engine.class);
        engine.start();
     }

    /**
     * Stops the workflow {@link Engine engine}.
     */
     public void stop() {
        engine.end();
     }

    /**
     * Entry point for running WorkHorse in a standalone,
     * non-OSGi fashion.
     * @param args Command-line arguements
     */
     public static void main(String[] args) {
         Bootstrap bootstrap = new Bootstrap();
         //TODO open shutdown port
         bootstrap.start();
     }
}
