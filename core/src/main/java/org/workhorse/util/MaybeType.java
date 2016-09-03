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
package org.workhorse.util;

import java.util.function.Consumer;

/**
 * A container object that contains an object of unknown type, which may
 * be of a desired type {@code T}.
 *
 * @author Brennan Spies
 */
public class MaybeType<T> {

    private Object instance;
    private Class<T> type;

    private MaybeType(Class<T> type, Object instance) {
       this.type = type;
       this.instance = instance;
    }

    /**
     * Returns a {@code MaybeType} of the desired type.
     * @param desiredType The class of the desired type
     * @param instance The object instance, cannot be null
     * @param <T> The desired type
     * @return The maybe type
     */
    public static <T> MaybeType<T> of(Class<T> desiredType, Object instance) {
        if(instance==null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return new MaybeType<>(desiredType, instance);
    }

    public void ifInstanceOf(Consumer<? super T> consumer) {
        if(type.isAssignableFrom(instance.getClass())) {
            consumer.accept(type.cast(instance));
        }
    }
}
