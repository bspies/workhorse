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

import com.google.inject.Guice;
import org.workhorse.Element;
import org.workhorse.config.TypeModule;
import org.workhorse.var.*;
import org.workhorse.util.Maps;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Base implementation of a {@link org.workhorse.exec.ctx.Context}.
 *
 * @author Brennan Spies
 */
public abstract class BaseContext<N extends Name> extends Element implements Context<N>
{
	private Map<QualifiedName,MutableVariable<?>> variables = Maps.newHashMap();
    private final VariableFactory variableFactory = initVariableFactory();

    /**
     * Creates the variable factory.
     * @return The variable factory
     */
    private VariableFactory initVariableFactory() {
        return Guice.createInjector(new TypeModule()).getInstance(VariableFactory.class);
    }

    protected VariableFactory getVariableFactory() {
        return variableFactory;
    }
	
	//adds or replaces value in variable map
	protected <T> void addOrReplace(QualifiedName qualifiedName, Class<T> type, T value) {
		variables.put(qualifiedName, variableFactory.createVariable(qualifiedName, type, value));
	}

    //adds or replaces value in variable map
    protected void addOrReplace(QualifiedName qualifiedName, Date value, DateType dateType) {
        variables.put(qualifiedName, variableFactory.createVariable(qualifiedName, value, dateType));
    }

    protected <T> void addOrReplace(MutableVariable<?> var) {
        variables.put(var.getName(), var);
    }
	
	//returns the value corresponding to the type/qualifiedName key
	protected <T> T getValue(Class<T> type, QualifiedName qualifiedName) {
		return type.cast(variables.get(qualifiedName).getValue());
	}

    @SuppressWarnings("unchecked")
	protected <T> MutableVariable<T> get(QualifiedName qualifiedName, Class<T> type) {
		return (MutableVariable<T>) variables.get(qualifiedName);
	}

    protected MutableVariable<?> get(QualifiedName qualifiedName) {
        return variables.get(qualifiedName);
    }

    protected Set<QualifiedName> getNames() {
        return variables.keySet();
    }

    protected boolean hasName(QualifiedName qualifiedName) {
        return variables.containsKey(qualifiedName);
    }
}
