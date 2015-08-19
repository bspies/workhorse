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
package org.workhorse.exec.ctx;

import org.workhorse.Identifiable;
import org.workhorse.var.Name;
import org.workhorse.var.Variable;

import java.util.Set;

/**
 * The variable context in a process, corresponding to a given block.
 * @param <N> The name type used to reference the variable
 */
public interface Context<N extends Name> extends Identifiable<Long>
{
    /**
	 * Checks to see if variable exists in this context.
	 *
     * @param name The variable name
     * @return True if variable exists in this context, false otherwise
	 */
	public boolean hasVariable(N name);

    /**
     * Returns the variable with the given name, or null if not
	 * defined in this context.
     * @param name The variable name
     * @param type The variable type
     * @return The variable or null
     */
    public <T> Variable<T> getVariable(N name, Class<T> type);
	
	/**
	 * Returns the variable with the given name, or null if not
	 * defined in this context.
	 *
     * @param name The variable name
     * @return The variable or null
	 */
	public Variable<?> getVariable(N name);

    /**
     * Returns the variable names present in this context.
     * @return The variable names
     */
    public Set<N> getVariableNames();
}
