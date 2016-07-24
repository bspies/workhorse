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
package org.workhorse.activity;

import org.workhorse.exec.ctx.MutableContext;

/**
 * Sub-interface of {@link Activity} for activities
 * that are not composites of other process elements.
 */
public interface AtomicActivity extends Activity {

    /**
     * Provides a <i>mutable</i> {@code Context}.
     * @return The activity context
     */
    @Override MutableContext getContext();
}
