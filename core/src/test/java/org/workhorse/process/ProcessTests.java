package org.workhorse.process;

import org.junit.Test;
import org.workhorse.dependency.DependencyManager;
import org.workhorse.dependency.guice.GuiceDependencyManager;
import org.workhorse.graph.ProcessDiagram;
import org.workhorse.graph.builder.actor.RoleBuilder;
import org.workhorse.graph.builder.actor.UserBuilder;
import org.workhorse.graph.builder.container.ProcessDiagramBuilder;
import org.workhorse.graph.builder.node.TaskNodeBuilder;
import org.workhorse.runtime.Engine;
import org.workhorse.runtime.WorkflowEngine;
import org.workhorse.test.util.TestIdGenerator;
import org.workhorse.type.Parameter;
import org.workhorse.util.Version;

/**
 * Test creating processes.
 */
public class ProcessTests {

    /**
     * Test executing process with single task.
     */
    @Test
    public void testProcessWithSingleTask() {
        String roleName = "Applicant",
                taskName = "Complete form",
                taskDesc = "Fill out form and turn it in",
                firstName = "Julia",
                lastName = "Strom";
        ProcessDiagram processDiagram = new ProcessDiagramBuilder(new TestIdGenerator())
                .withProcessName("ProcessWithSingleTask")
                .withVersion(Version.getDefault())
                .withParticipant(new UserBuilder().withName(firstName, lastName))
                .inRole(new RoleBuilder(roleName), path -> path.withNode(
                        new TaskNodeBuilder(taskName)
                                .withDescription(taskDesc)
                                .withInput(Parameter.of("foo", ctx -> ctx.getValue("foo").getValue()))
                                .withOutput(Parameter.of("bar", ctx -> ctx.getValue("bar").getValue()))
                ))
                .build();
        Engine workflowEngine = createEngine();
        Process process = workflowEngine.create(processDiagram);
        process.start();
    }

    private Engine createEngine() {
        DependencyManager dependencyManager = new GuiceDependencyManager();
        return new WorkflowEngine(dependencyManager);
    }
}
