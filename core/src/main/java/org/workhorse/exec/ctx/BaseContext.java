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
package org.workhorse.exec.ctx;

import org.workhorse.type.val.Value;

import java.util.Map;

/**
 * Base implementation of {@link Context}.
 *
 * @author Brennan Spies
 */
abstract class BaseContext implements Context {
    /**
     * Returns the map of constant values.
     * @return The constants map
     */
    protected abstract Map<String,Value> getConstants();
}
