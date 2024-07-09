package org.workhorse.process;

import org.junit.Test;
import org.workhorse.graph.ProcessDiagram;
import org.workhorse.graph.builder.container.ProcessDiagramBuilder;
import org.workhorse.test.util.TestIdGenerator;
import org.workhorse.util.Version;

public class ProcessTests {

    /**
     * Test executing process with single task.
     */
    @Test
    public void testProcessWithSingleTask() {
        ProcessDiagram processDiagram = new ProcessDiagramBuilder(new TestIdGenerator())
                .withProcessName("ProcessWithSingleTask")
                .withVersion(Version.getDefault())
                .addFlow();
    }
}
