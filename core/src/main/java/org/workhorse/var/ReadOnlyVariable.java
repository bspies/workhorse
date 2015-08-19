package org.workhorse.var;

/**
 * A read-only wrapper class for a mutable variable.
 *
 * @author Brennan Spies
 */
public class ReadOnlyVariable<T> implements Variable<T> {

    private MutableVariable<T> delegate;

    private ReadOnlyVariable(MutableVariable<T> mutableVariable) {
        this.delegate = mutableVariable;
    }

    public static <V> Variable<V> wrap(MutableVariable<V> mutableVariable) {
        return new ReadOnlyVariable<V>(mutableVariable);
    }

    @Override
    public QualifiedName getName() {
        return delegate.getName();
    }

    @Override
    public Class<T> getType() {
        return delegate.getType();
    }

    @Override
    public T getValue() {
        return delegate.getValue();
    }
}
