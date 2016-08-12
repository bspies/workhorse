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

import org.workhorse.id.Identifiable;
import org.workhorse.validation.Required;

import java.util.function.Supplier;

/**
 * Base builder for {@link Identifiable} types.
 *
 * @author Brennan Spies
 */
public abstract class BaseIdentifiableBuilder<T extends Identifiable<String>, B extends BaseIdentifiableBuilder<T,B>>
        extends BaseBuilder<B> implements ContextualBuilder<T> {

    @Required protected String id;

    /**
     * Sets the id for the build object
     * @param id The id to set
     * @return The builder
     */
    public B withId(String id) {
        this.id = id;
        return self();
    }

    protected String getId() { return id; }

    /**
     * Sets the id for the node if it is not already
     * specified.
     * @param f The function to produce the id
     */
    protected void setIdIfAbsent(Supplier<String> f) {
        if(this.id==null) {
            this.id = f.get();
        }
    }

    protected void setIdIfAbsent(BuilderContext ctx) {
        setIdIfAbsent(() -> ctx.generateIdFor(getBuiltType()));
    }
}
