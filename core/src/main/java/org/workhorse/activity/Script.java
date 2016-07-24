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
package org.workhorse.activity;

import org.workhorse.exec.Execution;
import org.workhorse.exec.RunState;
import org.workhorse.exec.ctx.MutableContext;
import org.workhorse.script.ScriptResource;

import java.util.UUID;

/**
 * An {@link Action} that executes a script.
 *
 * @author Brennan Spies
 */
public class Script extends BaseAtomicActivity implements Action
{	
	private ScriptResource scriptResource;

	/**
	 * Creates a {@code Script}.
	 * @param id The activity id
	 * @param name The activity name
	 * @param context The execution context
     * @param scriptRsrc The script resource
     */
	public Script(UUID id, String name, MutableContext context, ScriptResource scriptRsrc) {
		super(id, name, context);
		this.scriptResource = scriptRsrc;
	}

	/**
	 * Returns the script resource associated with
     * this instance.
	 * @return The script resource
     */
	public ScriptResource getScriptResource() {
		return scriptResource;
	}

    /**
     * Executes the script using the current context.
     * @param execution The current execution
     * @return The run state after execution
     */
	@Override public RunState perform(Execution execution) {

        return RunState.RUNNING;
	}


        /*for(LocalName varName : context.getVariableNames()) {
            scriptEngine.put(varName.getName(), context.getReadable(varName).getReadable());
        }
		try {
			scriptEngine.eval(scriptResource.getReader());
		} catch (Exception e) {
			throw new SystemException("Error executing script", e);
		}

        Bindings bindings = scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);
        for(Map.Entry<String,Object> bindingEntry : bindings.entrySet()) {
            if(!(bindingEntry.getReadable() instanceof ScriptContext))   {
                context.setVariable(bindingEntry.getKey(), bindingEntry.getReadable());
            }
        }*/
}
