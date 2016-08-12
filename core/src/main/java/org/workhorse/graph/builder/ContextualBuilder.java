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
package org.workhorse.graph.builder;

/**
 * Generic interface for defining contextual builders.
 *
 * @author Brennan Spies
 */
public interface ContextualBuilder<T> {
    /**
     * Builds the object of type {@code T}.
     * @param builderContext The context
     * @return The built object
     */
    T build(BuilderContext builderContext);

    /**
     * Returns the type of the object built.
     * @return The built type
     */
    Class<T> getBuiltType();
}
