package org.workhorse.bind;

import org.workhorse.util.TypeMap;

/**
 * @author Brennan Spies
 *
 * <p>An implementation of {@link Binding} that binds the object to global
 * JVM memory. </p>
 */
public class GlobalBinding<T> implements Binding<T>
{
    private static TypeMap bindingMap = new TypeMap();
    private Class<T> type;

    public GlobalBinding(Class<T> type) {
        this.type = type;
    }

    /**
     * @see org.workhorse.bind.Binding#bind(Object)
     */
    public void bind(T object) {
        bindingMap.put(type, object);
    }

    /**
     *  @see org.workhorse.bind.Binding#unbind() 
     */
    public void unbind() {
        bindingMap.remove(type);
    }

    /**
     * @see org.workhorse.bind.Binding#get()
     */
    public T get() {
        return bindingMap.get(type);  
    }
}
