package org.workhorse.activity.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workhorse.exec.ctx.ActivityContext;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the {@link Script} activity.
 */
public class ScriptTest
{
    private static Logger logger = LoggerFactory.getLogger(Script.class);

    //Utility method to list available scripting engines
    @BeforeClass
    public static void listEngines() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        logger.info("Listing all scripting engines");
        List<ScriptEngineFactory> factories = mgr.getEngineFactories();
        for (ScriptEngineFactory factory : factories) {
            logger.info("============================================");
            String engName = factory.getEngineName();
            String engVersion = factory.getEngineVersion();
            logger.info(String.format("Script Engine: %s (%s)", engName, engVersion));
            List<String> engNames = factory.getNames();
            for(String name: engNames) {
                logger.info(String.format("Engine Alias: %s", name));
            }
            for(String ext : factory.getExtensions()) {
                logger.info("File Extension: " + ext);
            }
        }
    }

    @Test
    public void testRubyStringScript() {
        ActivityContext ctx = new ActivityContext();
        ctx.setVariable("num", 6);
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptRef ref = ScriptRef.get("ruby", "$newnum = $num + 2");
        Script script = new Script("Test Script", mgr.getEngineByName("ruby"), ref);
        script.execute(ctx);
        //Test existing context variable stays the same
        assertEquals(6, ctx.getVariable("num").getValue());
        //Test created Ruby variable is passed to context
        assertEquals(8L, ctx.getVariable("newnum").getValue());
    }
}
