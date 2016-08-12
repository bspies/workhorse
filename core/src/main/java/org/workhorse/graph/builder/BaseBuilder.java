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

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import org.workhorse.validation.Required;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Base implementation of a builder, with common
 * utility methods.
 *
 * @author Brennan Spies
 */
public abstract class BaseBuilder<B extends BaseBuilder<B>> {
    /**
     * Common validation method for all builders
     * that checks required fields to ensure that
     * they are populated.
     * @return An iterable of validation errors, if any
     */
    protected Iterable<String> validate() {
        Collection<String> validationErrors = new ArrayList<>();
        Arrays.stream(this.getClass().getFields())
                .filter(field -> field.isAnnotationPresent(Required.class))
                .forEach(field -> {
                    try {
                        if(field.get(this)==null) {
                            validationErrors.add(String.format("Required field %s on builder %s cannot be null",
                                    field.getName(), this.getClass().getName()));
                        } else if (Collection.class.isAssignableFrom(field.getType())) {
                            if(Iterables.isEmpty((Iterable)field.get(this))) {
                                validationErrors.add(String.format("Required Iterable %s on builder %s cannot be empty",
                                        field.getName(), this.getClass().getName()));
                            }
                        } else if(Map.class.isAssignableFrom(field.getType())) {
                            if(((Map)field.get(this)).isEmpty()) {
                                validationErrors.add(String.format("Required Map %s on builder %s cannot be empty",
                                        field.getName(), this.getClass().getName()));
                            }
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(String.format("Unable to access field %s", field.getName()));
                    }
                });
        return validationErrors;
    }

    protected final void runValidation() {
        Iterable<String> errors = validate();
        if(!Iterables.isEmpty(errors)) {
            throw new RuntimeException(String.format("Builder has the following validation errors: %S",
                    Joiner.on(',').join(errors)));
        }
    }

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }
}
