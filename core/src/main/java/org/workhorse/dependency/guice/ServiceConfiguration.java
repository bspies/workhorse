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
package org.workhorse.dependency.guice;

import com.google.inject.AbstractModule;
import org.workhorse.script.JvmScriptRunner;
import org.workhorse.script.ScriptRunner;

/**
 * Configuration of core service functionality for the
 * {@link org.workhorse.dependency.GuiceDependencyManager}.
 */
public class ServiceConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(ScriptRunner.class).to(JvmScriptRunner.class);
    }
}
