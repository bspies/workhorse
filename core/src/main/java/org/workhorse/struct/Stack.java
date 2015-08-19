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
package org.workhorse.struct;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

/**
 * The Stack interface represents a last-in-first-out (LIFO) stack of objects.
 *
 * @author Brennan Spies
 */
public interface Stack<E> 
{
	/**
	 * Inspects the top of the stack without removing it.
	 * @return The item at the top of the stack
	 * @throws EmptyStackException If the stack is empty
	 */
	public E peek() throws EmptyStackException;
	
	/**
	 * Allows random access into stack for inspection.
	 * @param index The index of the item
	 * @return The item
	 */
	public E peek(int index);
	
	/**
	 * Removes the top of the stack and returns it.
	 * @return The item at the top of the stack
	 * @throws EmptyStackException If the stack is empty
	 */
	public E pop() throws EmptyStackException;
	
	/**
	 * Pushes an item onto the stack.
	 * @param item The item to push onto the stack
	 * @return The item added, or null if item could not be added
	 */
	public E push(E item);
	
	/**
	 * Pushes all the items of the {@link Collection} onto the stack
	 * in the order of iteration.
	 * @param items The items to push onto the stack
     * @return True if push successful
	 */
	public boolean pushAll(Collection<E> items);
	
	/**
	 * Empties the stack.
	 */
	public void clear();
	
	/**
	 * Returns the size of the stack.
	 * @return The size of the stack
	 */
	public int size();
	
	/**
	 * Returns a representation of the {@code Stack} as a {@code List}. 
	 * @return The list, starting from the bottom of the stack
	 */
	public List<E> asList();
}
