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
package org.workhorse.activity.factory;

import org.workhorse.activity.Activity;
import org.workhorse.exec.ctx.Context;
import org.workhorse.graph.exec.ActivityNode;

/**
 * Factory for creating {@link Activity} instances.
 *
 * @author Brennan Spies
 */
public interface ActivityFactory {
    /**
     * Creates the {@code Activity} from the given parent context.
     * @param node The activity node
     * @param ctx The parent context
     * @return The Activity instance
     */
    <T extends Activity> T createActivity(ActivityNode<T> node, Context ctx);
}
