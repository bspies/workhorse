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
 * Wrapper class to provide builder for existing object.
 *
 * @author Brennan Spies
 */
public class ExistingObject<T> implements ContextualBuilder<T> {

    private T existingObject;

    private ExistingObject(T existingObject) {
        this.existingObject = existingObject;
    }

    /**
     * Creates a wrapped builder around the existing object.
     * @param existingObject The existing object
     * @return The builder
     */
    public static <B> ContextualBuilder<B> wrap(B existingObject) {
        return new ExistingObject<>(existingObject);
    }

    /** Simply returns the wrapped object */
    @Override public T build(BuilderContext builderContext) {
        return existingObject;
    }

    /** {@inheritDoc} */
    @Override @SuppressWarnings("unchecked")
    public Class<T> getBuiltType() {
        return (Class<T>) existingObject.getClass();
    }
}
