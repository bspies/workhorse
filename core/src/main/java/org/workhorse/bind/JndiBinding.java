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
package org.workhorse.bind;

/**
 * @author Brennan Spies
 *
 * <p>Implementation of {@link Binding} that stores the bound object
 * in the JNDI directory tree.</p>
 */
public class JndiBinding<T> implements Binding<T> 
{
    private String jndiPath;

    public JndiBinding(String jndiPath) {
       this.jndiPath = jndiPath; 
    }

	public void bind(T object) {
		// TODO Auto-generated method stub
		
	}

	public T get() {
		// TODO Auto-generated method stub
		return null;
	}

	public void unbind() {
		// TODO Auto-generated method stub
		
	}
}
