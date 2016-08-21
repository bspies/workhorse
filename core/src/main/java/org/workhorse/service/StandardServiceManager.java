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
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import org.workhorse.util.Attribute;
import org.workhorse.util.NonUniqueResultException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Standard implementation of the {@link ServiceManager} interface.
 *
 * @author Brennan Spies
 */
public class StandardServiceManager implements ServiceManager {

    private ImmutableMultimap<Class<?>,QualifiedService<?>> services;

    /**
     * Default constructor, takes all of the services to be registered.
     * @param services The registered services
     */
    private StandardServiceManager(Multimap<Class<?>,QualifiedService<?>> services) {
        this.services = ImmutableMultimap
                .<Class<?>,QualifiedService<?>>builder()
                .putAll(services)
                .build();
    }

    /** {@inheritDoc} */
    @Override public <S> Optional<S> getService(Class<S> serviceType, Attribute<?>... attributes) {
        Collection<QualifiedService<?>> servicesOfType = services.get(serviceType);
        Collection<QualifiedService<?>> matchingRefs = attributes.length==0 ?
                servicesOfType :
                servicesOfType.stream()
                    .filter(sr -> doServiceAttributesMatch(sr, attributes))
                    .collect(Collectors.toList());
        if(matchingRefs.size()>1) {
            throw new NonUniqueResultException(String.format("Unable to find unique service from query attributes: %s",
                    Joiner.on(',').join(attributes)));
        }
        return matchingRefs.isEmpty() ? Optional.empty() :
                Optional.of(serviceType.cast(matchingRefs.iterator().next()));
    }

    /** {@inheritDoc} */
    @Override
    public <S> Collection<S> getServices(Class<S> serviceType) {
        return services.get(serviceType).stream()
                .map(qs->serviceType.cast(qs.getService()))
                .collect(Collectors.toList());
    }

    /**
     * Returns a builder for the service manager.
     * @return The builder
     */
    public static Builder builder() {
        return new Builder();
    }

    private boolean doServiceAttributesMatch(QualifiedService<?> sr, Attribute<?>[] queryAttrs) {
        return Arrays.stream(queryAttrs)
                .allMatch(queryAttr -> sr.getAttributes().stream()
                        .anyMatch(servAttr->servAttr.matches(queryAttr)));
    }

    /**
     * Builder for the service manager.
     */
    public static class Builder implements org.workhorse.util.Builder<ServiceManager> {

        private Multimap<Class<?>,QualifiedService<?>> services = HashMultimap.create();

        /**
         * Adds a registered service.
         * @param type The registered service type
         * @param service The service implementation
         * @param attributes Service attributes
         * @return The builder
         */
        public <T> Builder withService(Class<? super T> type, T service, Attribute<?>... attributes) {
            return withService(type, service, Arrays.asList(attributes));
        }

        /**
         * Adds a registered service.
         * @param type The registered service type
         * @param service The service implementation
         * @param attributes Service attributes
         * @return The builder
         */
        public <T> Builder withService(Class<? super T> type, T service, Iterable<Attribute<?>> attributes) {
            services.put(type, new QualifiedService<>(service, attributes));
            return this;
        }

        /** Builds service manager */
        @Override public ServiceManager build() {
            return new StandardServiceManager(services);
        }
    }

    /**
     * A reference to a registered service qualified by its
     * attributes.
     */
    private static class QualifiedService<T> {

        private T service;
        private Set<Attribute<?>> attributes;

        QualifiedService(T service, Iterable<Attribute<?>> attributes) {
            this.service = service;
            this.attributes = consume(attributes);
        }

        T getService() { return service; }

        Set<Attribute<?>> getAttributes() { return attributes; }

        private Set<Attribute<?>> consume(Iterable<Attribute<?>> attributes) {
            Set<Attribute<?>> attributeSet = new HashSet<>();
            Set<String> names = new HashSet<>();
            for(Attribute<?> attr : attributes) {
                if(!names.add(attr.getKey())) {
                    throw new IllegalArgumentException(
                            String.format("Cannot have duplicate attribute name (%s).", attr.getKey())
                    );
                }
            }
            return attributeSet;
        }
    }

}
