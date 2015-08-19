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

import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;

/**
 * @author Brennan Spies
 * <p>A control node where one or more outgoing transitions are chosen. May also have
 * a default transition, which is only taken if no other transition condition evaluates to true.
 * Also known as an Inclusive Gateway in BPMN.</p>
 */
public class MultiChoice extends Gateway 
{
	public MultiChoice(Graph parent, Lane lane) {
		super(parent, lane);
		// TODO Auto-generated constructor stub
	}
	
}
