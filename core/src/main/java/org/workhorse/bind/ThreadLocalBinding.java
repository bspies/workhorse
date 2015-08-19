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

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Brennan Spies
 *
 * <p>{@link Binding} implementation using thread local variables.</p>
 */
public class ThreadLocalBinding<T> implements Binding<T>
{
	private ThreadLocal<T> binding;
	
	/**
	 * Creates binding with no initial value
	 */
	public ThreadLocalBinding() {
		binding = new ThreadLocal<T>();
	}
	
	/**
	 * Creates binding with a provider for producing the initial value.
	 * @param provider The provider for the initial value
	 */
    @Inject
	public ThreadLocalBinding(final Provider<T> provider) {
		binding = new ThreadLocal<T>() {
			@Override protected T initialValue() {
				return provider.get();
			}
		};
	}

	/**
	 * @see org.workhorse.bind.Binding#bind(java.lang.Object)
	 */
	public void bind(T object) {
		binding.set(object);		
	}

	/**
	 * @see org.workhorse.bind.Binding#get()
	 */
	public T get() {
		return binding.get();
	}

	/**
	 * @see org.workhorse.bind.Binding#unbind()
	 */
	public void unbind() {
		binding.remove();
	}
}
