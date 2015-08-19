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
package org.workhorse.var;

import org.workhorse.expr.Expression;

/**
 * Represents a parameter (input or output).
 *
 * @author Brennan Spies
 */
public class Parameter<V> 
{	
	private String name;
	private Expression<V> expression;
	
	/**
	 * Returns the name of the parameter.
	 * @return  The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the expression used to evaluate the value of the parameter at runtime.
	 * @return  The expression
	 */
	public Expression<V> getExpression() {
		return expression;
	}
}
