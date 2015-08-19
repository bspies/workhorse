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
package org.workhorse.var.impl;

import org.workhorse.Element;
import org.workhorse.var.MutableVariable;
import org.workhorse.var.QualifiedName;
import org.workhorse.var.constraint.Constraint;

import javax.annotation.Nullable;

/**
 * Base class for implementations of {@link org.workhorse.var.Variable}.
 *
 * @author Brennan Spies
 */
public abstract class BaseVariable<T> extends Element implements MutableVariable<T>
{
    private QualifiedName qualifiedName;
    private Constraint<T> constraint;

    //internal use only
    protected BaseVariable() {}

    /**
     * Initializes the variable with the qualifiedName.
     * @param qualifiedName The variable qualifiedName
     */
    protected BaseVariable(QualifiedName qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    /**
     * Initializes the variable with the qualifiedName and the variable
     * constraint.
     * @param qualifiedName The variable qualifiedName
     * @param constraint The variable constraint
     */
    protected BaseVariable(QualifiedName qualifiedName, Constraint<T> constraint) {
        this.qualifiedName = qualifiedName;
        this.constraint = constraint;
    }

    /**
     * {@inheritDoc}
     */
    @Override public QualifiedName getName() {
        return qualifiedName;
    }

    /**
     * {@inheritDoc}
     */
    @Override public @Nullable Constraint<T> getConstraint() {
        return constraint;
    }

    /**
     * {@inheritDoc}
     */
    @Override public void setValue(T value) {
        validate(value);
        setValueInternal(value);
    }

    private void validate(T value) {
        if(constraint!=null && !constraint.isValid(value)) {

        }
    }

    /**
     * Sets the actual variable value.
     * @param value The value to set
     */
    protected abstract void setValueInternal(T value);
}
