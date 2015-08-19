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
package org.workhorse.expr;

import org.workhorse.exec.ctx.Context;
import org.workhorse.var.Name;

/**
 * A constant expression.
 *
 * @author Brennan Spies
 */
public class Constant<V> implements Expression<V> 
{
	private final V value;
	
	/**
	 * Default constructor.
	 * @param value The constant value
	 */
	public Constant(V value) {
		this.value = value;
	}
	
	/**
	 * @see Expression#evaluate(org.workhorse.exec.ctx.Context)
	 */
	public V evaluate(Context<? extends Name> context) {
		return value;
	}

}
