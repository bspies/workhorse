/*
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
import org.workhorse.exec.ctx.MutableContext;
import org.workhorse.graph.exec.ActivityNode;

import java.util.UUID;

/**
 * Functional interface for the creation of a specific {@link Activity}
 * sub-type.
 *
 * @author Brennan Spies
 */
@FunctionalInterface
public interface ActivityCreate<A extends Activity> {
    /**
     * @param instanceId The id of the created activity
     * @param node The activity node
     * @param ctx The activity context
     * @return The activity instance
     */
    A create(UUID instanceId, ActivityNode<A> node, MutableContext ctx);
}
