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
package org.workhorse.activity.assign;

import org.workhorse.activity.Task;

/**
 * Represents the algorithm used to assign a {@link Task}
 * to a particular {@link org.workhorse.actor.User}.
 */
public interface Assignment {
    /**
     * Assigns a {@code User} to the given task from
     * the given {@code Allocation}.
     * @param allocation The allocation to use
     * @param task The task to assign
     */
    void assign(Allocation allocation, Task task);
}
