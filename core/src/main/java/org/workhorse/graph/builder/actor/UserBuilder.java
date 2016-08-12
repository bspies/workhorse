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
package org.workhorse.graph.builder.actor;

import org.workhorse.actor.User;
import org.workhorse.graph.builder.BuilderContext;

/**
 * Builder for {@link User} instances.
 *
 * @author Brennan Spies
 */
public class UserBuilder extends BaseUserBuilder<User,UserBuilder> {

    /** {@inheritDoc} */
    @Override public User build(BuilderContext ctx) {
        setIdIfAbsent(ctx);
        runValidation();
        return new User(id, name);
    }

    /** {@inheritDoc} */
    @Override
    public Class<User> getBuiltType() {
        return User.class;
    }
}
