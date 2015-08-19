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
package org.workhorse.exec.ctx;

import org.workhorse.var.LocalName;
import org.workhorse.var.MutableVariable;
import org.workhorse.var.QualifiedName;
import org.workhorse.var.Variable;

import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of the {@link Context} interface that encapsulates the defined variables.
 *
 * @author Brennan Spies
 */
public class ActivityContext extends BaseContext<LocalName> implements LocalContext
{
    private String namespace;

    /**
     * The namespace of this context.
     * @return The namespace
     */
    public String getNamespace() {
        return namespace;
    }

    public <T> void setVariable(String simpleName, T value) {
        setVariable(new LocalName(simpleName), value);
    }

    /**
	 * Sets the existing variable, or creates a new one if none
	 * defined in the current context.
     * @param localName The local name of the variable
     * @param value The value of the variable
     */
	@SuppressWarnings("unchecked")
    @Override
	public <T> void setVariable(LocalName localName, T value) {
		Class<T> type = (Class<T>)value.getClass();
		Variable<T> var = getVariable(localName, type);
        QualifiedName varQualifiedName = new QualifiedName(null, localName);
		if(var==null) {
			addOrReplace(varQualifiedName, type, value);
		} else {
            validateAssignment(value, type, var);
            addOrReplace(varQualifiedName, type, value);
		}
	}

    private <T> void validateAssignment(T value, Class<T> type, Variable<T> var) {
        if(var.getValue()!=null && !var.getType().equals(type))
            throw new IllegalArgumentException(String.format(
                    "Attempt to set variable '%s' of type %s with value of type %s",
                    var.getName(),
                    var.getType().getName(),
                    var.getClass().getName()));
    }

    /**
     * Returns true if this context contains a variable with the given name.
     * @param name The variable name
     * @return True if this context contains the variable
     */
    @Override
    public boolean hasVariable(LocalName name) {
        return this.hasName(getLocalName(name));
    }

    /**
     * Returns the variable with the given local name, or null if no such
     * variable.
     * @param localName The variable name
     * @return The variable, or null
     */
    @Override
    public MutableVariable<?> getVariable(String localName) {
        return getVariable(new LocalName(localName));
    }

    /**
	 * Returns the variable with the given name, or null if not
	 * defined in this context.
	 *
     * @param name The variable name
     * @return The variable or null
	 */
    @Override
	public MutableVariable<?> getVariable(LocalName name) {
		return get(getLocalName(name));
	}

    @Override
    public <T> MutableVariable<T> getVariable(LocalName name, Class<T> type) {
        return get(getLocalName(name), type);
    }

    /**
     * Return all the variable names in this context.
     * @return All the variable names
     */
    @Override
    public Set<LocalName> getVariableNames() {
        Set<LocalName> varNames = new HashSet<LocalName>();
        for(QualifiedName qn : getNames()) {
            varNames.add(new LocalName(qn.getLocalName()));
        }
        return varNames;
    }

    private QualifiedName getLocalName(LocalName localName) {
        return new QualifiedName(null, localName);
    }
}
