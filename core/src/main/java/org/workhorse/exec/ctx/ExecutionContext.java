package org.workhorse.exec.ctx;

import org.workhorse.var.*;

import java.util.Set;

/**
 * @author Brennan Spies
 */
public class ExecutionContext extends BaseContext<QualifiedName> {

    @Override public boolean hasVariable(QualifiedName qualifiedName) {
        return hasName(qualifiedName);
    }

    @Override public Variable<?> getVariable(QualifiedName qualifiedName) {
        return ReadOnlyVariable.wrap(get(qualifiedName));
    }

    @Override public <T> Variable<T> getVariable(QualifiedName qualifiedName, Class<T> type) {
        return ReadOnlyVariable.wrap(get(qualifiedName, type));
    }

    @Override
    public Set<QualifiedName> getVariableNames() {
        return getNames();
    }

    @SuppressWarnings("unchecked")
    public void setActivityOutput(ActivityContext context, Set<Parameter<?>> outputParams) {
        for(Parameter<?> param : outputParams) {
            QualifiedName qualifiedName = new QualifiedName(context.getNamespace(), param.getName());
            Object value = param.getExpression().evaluate(context);
            MutableVariable var = getVariableFactory().createVariable(qualifiedName, value.getClass());
            var.setValue(value);
            addOrReplace(var);
        }
    }
}
