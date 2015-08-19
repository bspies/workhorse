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
package org.workhorse.activity;

import java.util.Collection;

import org.workhorse.actor.User;
import org.workhorse.exec.ctx.Context;

/**
 * @author Brennan Spies
 *<p>
 * An <code>Allocation</code> respresents the designation of potential resources
 * for the completion of a {@link Task}. If more than one resource is allocated
 * some method, such as voluntary acquisition, may be used to determine the actual
 * task performer.
 * </p>
 */
public interface Allocation 
{	
	/**
	 * Gets the potential users who can perform the <code>Task</code>.
	 * @param context The variable context
     * @return The potential task performers
	 */
	public Collection<User> determine(Context context);
}
