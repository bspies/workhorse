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
package org.workhorse.service;

import org.workhorse.util.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager for {@link Service} instances. Each service is registered uniquely by
 * its type.
 *
 * @author Brennan Spies
 */
public class ServiceManager
{
    private Map<Class<? extends Service>, Service> registry;

    /**
     * Default constructor.
     */
    public ServiceManager() {
        registry = Maps.newHashMap();
    }

    /**
     * Registers a {@code Service}.
     * @param service The service instance
     * @param <S>  The service type
     */
    public <S extends Service> void registerService(S service) {
        registry.put(service.getClass(), service);
    }

    /**
     * Gets a service.
     * @param serviceType The class of the service interface
     * @param <S> The service type
     * @return The service implementation
     */
    @SuppressWarnings("unchecked")
    public <S extends Service> S getService(Class<S> serviceType) {
        return (S)registry.get(serviceType);
    }

    /**
     * Starts all registered services.
     */
    public void startServices() {
        for(Service s : registry.values()) {
            s.start();
        }
    }

    /**
     * Shuts down all regsitered services.
     */
    public void stopServices() {
        for(Service s : registry.values()) {
            s.shutdown();
        }
    }
}
