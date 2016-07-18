/*
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Simple implementation of a {@link Stack} backed by an {@link ArrayList}.
 *
 * @author Brennan Spies
 */
public class ArrayStack<E> implements Stack<E>, Cloneable, Serializable
{
	private List<E> list;
	
	/**
	 * Creates a new {@code ArrayStack}.
	 */
	public ArrayStack() {
		list = new ArrayList<>();
	}
	
	/**
	 * Creates a new {@code ArrayStack} by wrapping the
	 * given list. This will result in the first element of
	 * the list becoming the bottom of the stack.
	 * 
	 * @param list The list to wrap
	 */
	public ArrayStack(List<E> list) {
		this.list = list;
	}
	
	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * @see org.workhorse.struct.Stack#peek()
	 */
	@Override
	public E peek() throws EmptyStackException {
		checkNotEmpty();
		return list.get(list.size() - 1);
	}

	/**
	 * @see org.workhorse.struct.Stack#peek(int)
	 */
	@Override
	public E peek(int index) {
		return list.get(index);
	}

	/**
	 * @see org.workhorse.struct.Stack#pop()
	 */
	@Override
	public E pop() throws EmptyStackException {
		checkNotEmpty();
		return list.remove(list.size()-1);
	}

	//throws exception if stack is empty
	private void checkNotEmpty() {
		if(list.size()==0)
			throw new EmptyStackException();
	}

	/**
	 * @see org.workhorse.struct.Stack#push(java.lang.Object)
	 */
	@Override
	public E push(E item) {
		if(list.add(item))
			return item;
		return null;
	}

	/**
	 * @see org.workhorse.struct.Stack#pushAll(java.util.Collection)
	 */
	@Override
	public boolean pushAll(Collection<E> items) {
		boolean b = true;
		for(E item : items)
			b &= list.add(item);
		return b;
	}

    @Override
	public List<E> asList() {
        return list;
    }
	
	/**
	 * @see org.workhorse.struct.Stack#clear()
	 */
    @Override
	public void clear() {
		list.clear();
	}

	/**
	 * @see org.workhorse.struct.Stack#size()
	 */
    @Override
	public int size() {
		return list.size();
	}

}
