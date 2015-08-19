/**
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
package org.workhorse.sequence;

import org.workhorse.exec.ctx.Context;

/**
 * Interface representing a sequence processor that returns the next step
 * in the execution.
 *
 * @author Brennan Spies
 */
public interface Processor {
	/**
	 * Evaluates next step, optionally using the context.
	 * Processor is finished when null is returned.
	 * @param context The execution context
	 * @return The next step, or null if none
	 */
	public Step next(Context context);
}
