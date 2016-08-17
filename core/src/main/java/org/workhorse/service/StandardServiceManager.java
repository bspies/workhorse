/*
 * Copyright (c) 2009-2016 Brennan Spies
 *
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

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.workhorse.util.Attribute;
import org.workhorse.util.NonUniqueResultException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Standard implementation of the {@link ServiceManager} interface.
 *
 * @author Brennan Spies
 */
public class StandardServiceManager implements ServiceManager {

    private Multimap<Class<?>,ServiceReference<?>> services;

    public StandardServiceManager() {
        services = HashMultimap.create();
    }

    /** {@inheritDoc} */
    @Override public <S> S getService(Class<S> serviceType, Attribute<?>... attributes) {
        Collection<ServiceReference<?>> servicesOfType = services.get(serviceType);
        Collection<ServiceReference<?>> matchingRefs = attributes.length==0 ? servicesOfType :
                servicesOfType.stream()
                    .filter(sr -> doServiceAttributesMatch(sr, attributes))
                    .collect(Collectors.toList());
        if(matchingRefs.size()>1) {
            throw new NonUniqueResultException(String.format("Unable to find unique service from query attributes: %s",
                    Joiner.on(',').join(attributes)));
        }
        return matchingRefs.isEmpty() ? null : serviceType.cast(matchingRefs.iterator().next()); //todo need Optional?
    }

    private boolean doServiceAttributesMatch(ServiceReference<?> sr, Attribute<?>[] queryAttrs) {
        return Arrays.stream(queryAttrs)
                .allMatch(queryAttr -> sr.getAttributes().stream()
                        .anyMatch(servAttr->servAttr.matches(queryAttr)));
    }

    /**
     * Registers a service.
     * @param service The service to register
     * @param attributes The service attributes
     */
    public void registerService(Object service, Iterable<Attribute<?>> attributes) {
        services.put(service.getClass(), new ServiceReference<>(service, attributes));
    }

    /**
     * A reference to a registered service and its
     * attributes.
     */
    private static class ServiceReference<T> {

        T service;
        Set<Attribute<?>> attributes;

        ServiceReference(T service, Iterable<Attribute<?>> attributes) {
            this.service = service;
        }

        T getService() { return service; }

        Set<Attribute<?>> getAttributes() { return attributes; }
    }

}
