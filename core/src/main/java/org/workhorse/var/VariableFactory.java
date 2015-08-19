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
package org.workhorse.var;

import org.workhorse.SystemException;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

/**
 * Factory for {@link Variable} implementations.
 *
 * @author Brennan Spies
 */
public class VariableFactory
{
    private Map<VariableKey,Class<? extends MutableVariable<?>>> varTypes;

    /**
     * Creates the variable factory.
     * @param varTypes The mapping of native types to variable types
     */
    @Inject
    public VariableFactory(Map<VariableKey,Class<? extends MutableVariable<?>>> varTypes) {
        this.varTypes = varTypes;
    }

    private VariableKey getKey(Class<?> keyClass) {
        return VariableKey.get(keyClass);
    }

    private VariableKey getKey(Class<?> keyClass, Object qualifier) {
        return VariableKey.get(keyClass, qualifier);
    }

    /**
     * Creates an initialized variable.
     * @param qualifiedName The variable qualifiedName
     * @param type The variable Java class
     * @param value The initial value
     * @return The variable
     */
    public <T> MutableVariable<T> createVariable(QualifiedName qualifiedName, Class<T> type, T value) {
       MutableVariable<T> var = createVariable(qualifiedName, type);
       var.setValue(value);
       return var;
    }

    /**
     * Creates a date/time variable.
     * @param qualifiedName The variable qualifiedName
     * @param value The variable value
     * @param dateType The date/time type
     * @return The variable
     */
    public MutableVariable<Date> createVariable(QualifiedName qualifiedName, Date value, DateType dateType) {
       MutableVariable<Date> dateVar = createVariable(qualifiedName, dateType);
       dateVar.setValue(value);
       return dateVar;
    }

    /**
     * Creates an uninitialized date/time variable.
     * @param qualifiedName the variable qualifiedName
     * @param dateType The date/time type
     * @return The date/time variable
     */
    public MutableVariable<Date> createVariable(QualifiedName qualifiedName, DateType dateType) {
       @SuppressWarnings("unchecked")
       Class<MutableVariable<Date>> type = (Class<MutableVariable<Date>>)varTypes.get(getKey(Date.class, dateType));
       if(type==null) {
           throw new IllegalArgumentException(String.format("No variable type defined for %s of type %s",
                   Date.class.getCanonicalName(), dateType));
       }
       return newInstance(type, qualifiedName);
    }

    /**
     * Creates an uninitialized variable.
     * @param qualifiedName The variable qualifiedName
     * @param varType The variable Java class
     * @return The variable
     */
    public <T> MutableVariable<T> createVariable(QualifiedName qualifiedName, Class<T> varType) {
       @SuppressWarnings("unchecked")
       Class<MutableVariable<T>> type = (Class<MutableVariable<T>>)varTypes.get(getKey(varType));
       if(type==null) {
           throw new IllegalArgumentException(String.format("No variable type defined for %s",
                   varType.getCanonicalName()));
       }
       return newInstance(type, qualifiedName);
    }

    private <T> T newInstance(Class<T> type, QualifiedName qualifiedName) {
        try {
          return type.getConstructor(QualifiedName.class).newInstance(qualifiedName);
        } catch (Throwable t) {
            throw new SystemException(String.format("Unable to initialize variable of type '%s'",
                    type.getCanonicalName()), t);
        }
    }
}
