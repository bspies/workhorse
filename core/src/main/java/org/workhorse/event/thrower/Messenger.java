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
package org.workhorse.event.thrower;

import org.workhorse.connect.Connector;
import org.workhorse.connect.SendMode;
import org.workhorse.event.Message;
import org.workhorse.exec.Execution;

/**
 * @author Brennan Spies
 *
 */
public class Messenger<T> implements Thrower<Message<T>> 
{
	private Connector connector;
	private SendMode mode;
	
	/**
	 * @see org.workhorse.event.thrower.Thrower#throwEvent(org.workhorse.exec.Execution)
	 */
	public void throwEvent(Execution execution) {
		// TODO Auto-generated method stub
		
	}
}
