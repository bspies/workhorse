/*
 * Copyright (c) 2009-2016 Brennan Spies
 *
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
package org.workhorse.type;

import org.workhorse.exec.ctx.Context;
import org.workhorse.expr.Expression;
import org.workhorse.type.constraint.Constraint;
import org.workhorse.type.constraint.ConstraintViolation;

import java.util.Optional;

/**
 * Represents an input or output parameter.
 *
 * @author Brennan Spies
 */
public class Parameter<V> {

    private final Expression<V> expression;
    private Constraint<V> constraint;
    private final String name;

    /**
     * Creates a parameter with a name and expression.
     * @param name The parameter name
     * @param expression The expression to evaluate
     */
    public Parameter(String name, Expression<V> expression) {
        this.expression = expression;
        this.name = name;
    }

    /**
     * Factory method to create a parameter.
     * @param name The parameter name
     * @param expr The parameter expression
     * @return The parameter
     */
    public static <T> Parameter<T> of(String name, Expression<T> expr) {
        return new Parameter<>(name, expr);
    }

    /**
     * The name of the parameter.
     * @return Parameter name
     */
    public String getName() {
        return name;
    }

    /**
     * Evaluates the parameter expression to return a value.
     * @param context The context to use for evaluation
     * @return The output value of the expression
     */
    public V evaluate(Context context) {
        V value = expression.evaluate(context);
        Optional<Constraint<V>> c = getOptionalConstraint();
        if(c.isPresent() && !c.get().isValid(value)) {
            throw new ConstraintViolation(constraint, value);
        }
        return value;
    }

    /**
     * Sets the value constraint for this parameter.
     * @param constraint The value constraint
     */
    public void setConstraint(Constraint<V> constraint) {
        this.constraint = constraint;
    }

    /**
     * Gets the optional constraint for this parameter.
     * @return The constraint
     */
    private Optional<Constraint<V>> getOptionalConstraint() {
        return Optional.ofNullable(constraint);
    }
}
