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
package org.workhorse.dependency;

import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Implementation of the {@link DependencyManager} service using the Google Guice
 * dependency injection framework.
 */
public class GuiceDependencyManager implements DependencyManager {

    private final Injector injector;

    /**
     * Creates the Guice dependency manager with the configuration
     * modules.
     * @param modules The configuration modules
     */
    public GuiceDependencyManager(Module... modules) {
        final DependencyManager dp = this;
        injector = Guice.createInjector(Lists.asList(new AbstractModule() {
            @Override protected void configure() {
                bind(DependencyManager.class).toInstance(dp);
            }
        }, modules));
    }

    /** {@inheritDoc} */
    @Override public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }
}
