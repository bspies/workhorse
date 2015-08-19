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
package org.workhorse.graph;

import org.workhorse.exec.Execution;

/**
 * @author Brennan Spies
 * 
 * <p>Listener for transition events.</p>
 */
public interface TransitionListener 
{
	/**
	 * Method called 
	 * @param sequenceFlow The transition being fired
	 * @param execution The execution taking the transition
	 */
	public void onTransition(SequenceFlow sequenceFlow, Execution execution);
}
