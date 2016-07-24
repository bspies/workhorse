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

/**
 * Manages application dependencies.
 *
 * @author Brennan Spies
 */
public interface DependencyManager {
    /**
     * Creates or gets (e.g. singleton) an instance
     * of the given type.
     * @param type The instance type
     * @return The instance
     */
    <T> T getInstance(Class<T> type);
}
