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
package org.workhorse.activity.impl;

import javax.script.ScriptEngine;
import javax.script.ScriptContext;
import javax.script.Bindings;

import org.workhorse.activity.Action;
import org.workhorse.SystemException;
import org.workhorse.exec.ctx.LocalContext;
import org.workhorse.var.LocalName;

import java.util.Map;

/**
 * An {@link Action} that executes a script.
 *
 * @author Brennan Spies
 */
public class Script extends BaseActivity implements Action 
{	
	private ScriptRef scriptRef;
	private ScriptEngine scriptEngine;
	
	public Script(String name, ScriptEngine engine, ScriptRef scriptRef) {
		super(name);
        this.scriptEngine = engine;
		this.scriptRef = scriptRef;
	}

	/**
	 * @see org.workhorse.activity.Action#execute(org.workhorse.exec.ctx.LocalContext)
	 */
	public void execute(LocalContext context)
    {
        for(LocalName varName : context.getVariableNames()) {
            scriptEngine.put(varName.getName(), context.getVariable(varName).getValue());
        }
		try {
			scriptEngine.eval(scriptRef.getReader());
		} catch (Exception e) {
			throw new SystemException("Error executing script", e);
		}

        Bindings bindings = scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);
        for(Map.Entry<String,Object> bindingEntry : bindings.entrySet()) {
            if(!(bindingEntry.getValue() instanceof ScriptContext))   {
                context.setVariable(bindingEntry.getKey(), bindingEntry.getValue());
            }
        }
	}
}
