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
package org.workhorse.graph.control;

import org.workhorse.sequence.SequenceBuilder;


/**
 * Represents a control structure for a node, e.g. for looping or creating
 * multiple instances.
 *
 * @author Brennan Spies
 */
public interface NodeControl 
{
	/**
	 * Returns the appropriate sequence builder for the
	 * node control.
	 */
	public SequenceBuilder getBuilder();
}
