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
package org.workhorse.script;

import org.workhorse.activity.Script;
import org.workhorse.exec.ctx.Context;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Default implementation of {@link ScriptRunner}, uses the JDK's
 * scripting API.
 *
 * @author Brennan Spies
 */
public class JvmScriptRunner implements ScriptRunner {

    private final ScriptEngineManager mgr = new ScriptEngineManager();

    /**
     * Executes the script.
     * @param script The script
     */
    @Override public void execute(Script script) {
        //todo implement
        ScriptResource rsc = script.getScriptResource();
        ScriptEngine engine = mgr.getEngineByName("");  //todo put "language" or MIME on ScriptResource
        Context context = script.getContext();
        context.getNames().forEach(n ->  engine.put(n, context.getReadable(n).getValue()));

        try {
            engine.eval(rsc.getReader());
            Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
            //put bindings into current context
        } catch (Exception e) {
            //throw system exception
        }
    }
}
