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
package org.workhorse.exec;

import java.util.Set;
import java.io.Serializable;

import org.workhorse.var.Parameter;

/**
 * Interface used for a process element that maintains
 * its own variable context.
 *
 * @author Brennan Spies
 */
public interface Block
{
	/**
	 * Returns the parameters used to determine
	 * the output from a block.
	 * @return The output parameters
	 */
	public Set<Parameter<?>> getOutputSet();
	
	/**
	 * Returns the parameters used to determine the
	 * input to a block.
	 * @return The input parameters
	 */
	public Set<Parameter<?>> getInputSet();
}
