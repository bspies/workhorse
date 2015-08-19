package org.workhorse.factory.impl;

import org.workhorse.factory.ActionFactory;
import org.workhorse.activity.impl.Script;
import org.workhorse.activity.impl.ScriptRef;

import javax.script.ScriptEngineManager;

/**
 * Factory for creating {@link Script} instances.
 */
public class ScriptFactory implements ActionFactory<Script>
{
    private ScriptEngineManager scriptEngineManager;
    private ScriptRef scriptReference;
    private String name;

    public ScriptFactory(String name, ScriptRef scriptRef) {
        this.name = name;
        this.scriptReference = scriptRef;
        scriptEngineManager = new ScriptEngineManager();
    }

    /**
     * @see org.workhorse.factory.ActionFactory#createAction()
     */
    public Script createAction() {
        return new Script(name, scriptEngineManager.getEngineByName(""), scriptReference);
    }
}
