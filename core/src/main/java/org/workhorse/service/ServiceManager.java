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

import org.workhorse.util.Attribute;
import org.workhorse.util.NonUniqueResultException;

import java.util.Collection;
import java.util.Optional;

/**
 * An interface for managing process services. Multiple services per
 * service interface is allowed as long as the services are distinguished
 * by a unique set of {@link Attribute} values.
 *
 * @author Brennan Spies
 */
public interface ServiceManager {

    /**
     * Gets a service of the given type with the given attributes. If no
     * attributes are provided, this method will return a registered service
     * with any attributes.
     * @param serviceType The registered service type
     * @param attributes The service attributes to match
     * @return The optional service instance
     * @throws NonUniqueResultException If attributes match more than one service
     */
    <S> Optional<S> getService(Class<S> serviceType, Attribute<?>... attributes);

    /**
     * Gets all services of the given type.
     * @param serviceType The registered service type
     * @return The services
     */
    <S> Collection<S> getServices(Class<S> serviceType);
}
