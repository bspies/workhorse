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
package org.workhorse.exec.ctx.factory;

import org.workhorse.activity.Activity;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.ctx.MutableContext;
import org.workhorse.type.Parameter;
import org.workhorse.type.val.Value;

/**
 * Factory interface for creating {@link org.workhorse.exec.ctx.Context}
 * instances.
 */
public interface ContextFactory {
    /**
     * Create a mutable context for an {@link Activity}
     * instance.
     * @param type The activity type
     * @param parent The parent context used to evaluate input
     * @param inputParams The input parameters
     * @return The mutable context
     */
    public <A extends Activity> MutableContext createActivityContext(
            Class<A> type, Context parent, Iterable<Parameter<?>> inputParams);

    /**
     * Creates a top-level {@link org.workhorse.process.Process} context.
     * @param initConstants The initial constants (if any)
     * @return The mutable context
     */
    public MutableContext createProcessContext(Iterable<Value<?>> initConstants);
}
