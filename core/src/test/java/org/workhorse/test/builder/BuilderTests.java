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
package org.workhorse.test.builder;

import org.junit.Test;
import org.workhorse.actor.Actor;
import org.workhorse.actor.User;
import org.workhorse.actor.UserGroup;
import org.workhorse.graph.*;
import org.workhorse.graph.builder.actor.DepartmentBuilder;
import org.workhorse.graph.builder.actor.RoleBuilder;
import org.workhorse.graph.builder.actor.UserBuilder;
import org.workhorse.graph.builder.container.ProcessDiagramBuilder;
import org.workhorse.graph.builder.node.TaskNodeBuilder;
import org.workhorse.graph.exec.TaskNode;
import org.workhorse.test.util.TestIdGenerator;
import org.workhorse.util.Version;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the builder API.
 *
 * @author Brennan Spies
 */
public class BuilderTests {

    /**
     * Test building a simple workflow with a single pool, a single lane, and
     * only one node in that lane.
     */
    @Test
    public void testDiagramWithSingleUserRoleAndTask() {

        String roleName = "Applicant",
               taskName = "Complete form",
               taskDesc = "Fill out form and turn it in",
               firstName = "Aaron",
               lastName = "Wilde";

        ProcessDiagram processDiagram = new ProcessDiagramBuilder(new TestIdGenerator())
                .withProcessName("Test simple workflow")
                .withVersion(Version.getDefault())
                .withParticipant(new UserBuilder().withName(firstName, lastName))
                    .inRole(new RoleBuilder(roleName), path -> path.withNode(
                        new TaskNodeBuilder(taskName).withDescription(taskDesc)
                    ))
                .build();

        assertThat(processDiagram).isNotNull();
        assertThat(processDiagram.getPools()).as("Diagram should have 1 pool").hasSize(1);
        assertThat(processDiagram.getNodes()).as("Diagram should have 1 node").hasSize(1);

        Pool onlyPool = processDiagram.getPools().iterator().next();
        Actor actor = onlyPool.getParticipant();
        assertThat(actor).isNotNull();
        assertThat(actor).as("Actor should be User").isInstanceOf(User.class);
        assertThat(actor.getDisplayName()).contains(firstName, lastName);
        assertThat(onlyPool.getLanes()).as("Should have exactly one Lane").hasSize(1);

        Lane onlyLane = onlyPool.getLanes().iterator().next();
        assertThat(onlyLane.getRole()).isNotNull();
        assertThat(onlyLane.getRole().getName()).isEqualTo(roleName);

        Node onlyNode = processDiagram.getNodes().iterator().next();
        assertNodeCorrect(onlyNode, TaskNode.class, taskName, taskDesc, onlyLane);
    }

    /**
     * Test building a simple workflow with a single pool, a single lane, and multiple
     * tasks connected by sequence flows.
     */
    @Test
    public void testDiagramWithSingleUserRoleAndTaskSequence() {

        String roleName = "Applicant",
               taskName1 = "Complete form",
               taskDesc1 = "Fill out form and turn it in",
               taskName2 = "Hand in form",
               taskDesc2 = "Hand in the form to form processor",
               firstName = "Aaron",
               lastName = "Wilde";

        ProcessDiagram processDiagram = new ProcessDiagramBuilder(new TestIdGenerator())
                .withProcessName("Test simple workflow")
                .withVersion(Version.getDefault())
                .withParticipant(new UserBuilder().withName(firstName, lastName))
                    .inRole(new RoleBuilder(roleName), path ->
                        path.withStart("Start")
                            .withFlow(new TaskNodeBuilder(taskName1).withDescription(taskDesc1))
                            .withFlow(new TaskNodeBuilder(taskName2).withDescription(taskDesc2))
                    )
                .build();

        assertThat(processDiagram).isNotNull();
        assertThat(processDiagram.getPools()).as("Diagram should have 1 pool").hasSize(1);
        assertThat(processDiagram.getNodes()).as("Diagram should have 3 nodes").hasSize(3);
        assertThat(processDiagram.getFlows()).as("Diagram should have 2 sequence flows").hasSize(2);

        Pool onlyPool = processDiagram.getPools().iterator().next();
        Actor actor = onlyPool.getParticipant();
        assertThat(actor).isNotNull();
        assertThat(actor).as("Actor should be User").isInstanceOf(User.class);
        assertThat(actor.getDisplayName()).contains(firstName, lastName);
        assertThat(onlyPool.getLanes()).as("Should have exactly one Lane").hasSize(1);

        Lane onlyLane = onlyPool.getLanes().iterator().next();
        assertThat(onlyLane.getRole()).isNotNull();
        assertThat(onlyLane.getRole().getName()).isEqualTo(roleName);
    }

    /**
     * Tests a diagram with multiple swim lanes and tasks, but no flows.
     */
    @Test
    public void testDiagramWithUserMultipleRolesAndTasksNoFlows() {

        String deptName = "Finance",
               roleName1 = "Team Assistant",
               roleName2 = "Invoice Approver",
               roleName3 = "Accountant",
               reviewTaskName = "Review Invoice",
               reviewTaskDesc = "Review submitted invoice",
               approveTaskName = "ApproveInvoice",
               approveTaskDesc = "Approve or reject submitted invoice",
               transferTaskName = "Complete Bank Transfer",
               transferTaskDesc = "Transfer funds to recipient bank account";

        ProcessDiagram processDiagram = new ProcessDiagramBuilder(new TestIdGenerator())
                .withProcessName("Test invoice workflow")
                .withVersion(Version.getDefault())
                .withParticipant(new DepartmentBuilder().withName(deptName))
                    .inRole(new RoleBuilder(roleName1), path -> path.withNode(
                        new TaskNodeBuilder(reviewTaskName).withDescription(reviewTaskDesc)
                    ))
                    .inRole(new RoleBuilder(roleName2), path -> path.withNode(
                        new TaskNodeBuilder(approveTaskName).withDescription(approveTaskDesc)
                    ))
                    .inRole(new RoleBuilder(roleName3), path -> path.withNode(
                        new TaskNodeBuilder(transferTaskName).withDescription(transferTaskDesc)
                    ))
                .build();

        assertThat(processDiagram).isNotNull();
        assertThat(processDiagram.getNodes()).hasSize(3);

        Collection<Pool> pools = processDiagram.getPools();
        assertThat(pools).as("Should be exactly one Pool").hasSize(1);

        Pool onlyPool = pools.iterator().next();
        Actor actor = onlyPool.getParticipant();
        assertThat(actor).isNotNull();
        assertThat(actor).as("Actor should be UserGroup").isInstanceOf(UserGroup.class);
        assertThat(actor.getDisplayName()).isEqualTo(deptName);
        assertThat(onlyPool.getLanes()).as("Should have exactly 3 Lanes").hasSize(3);

        assertNodeCorrect(processDiagram, TaskNode.class, reviewTaskName, reviewTaskDesc,
                getLaneByRoleName(processDiagram, roleName1));
        assertNodeCorrect(processDiagram, TaskNode.class, approveTaskName, approveTaskDesc,
                getLaneByRoleName(processDiagram, roleName2));
        assertNodeCorrect(processDiagram, TaskNode.class, transferTaskName, transferTaskDesc,
                getLaneByRoleName(processDiagram, roleName3));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    // Internal helper methods
    ///////////////////////////////////////////////////////////////////////////////////////

    /** Asserts that node values are correct */
    private void assertNodeCorrect(Node node, Class<? extends Node> type,
                                   String name, String desc, Lane lane) {
        assertThat(node).as("Node should not be null").isNotNull();
        assertThat(node).as("Node type incorrect").isInstanceOf(type);
        assertThat(node.getName()).as("Node name not correct").isEqualTo(name);
        assertThat(node.getDescription()).as("Node description is not correct").isEqualTo(desc);
        assertThat(node.getLane()).as("Node lane cannot be null").isNotNull();
        assertThat(node.getLane()).as("Node has incorrect lane").isEqualTo(lane);
    }

    /** First finds the node with the given name, then asserts values are correct. */
    private void assertNodeCorrect(Diagram diagram, Class<? extends Node> type,
                                   String name, String desc, Lane lane) {
        Optional<Node> nodeOptional = diagram.getNodes().stream().filter(n -> name.equals(n.getName())).findFirst();
        assertNodeCorrect(nodeOptional.orElseThrow(() -> new AssertionError(String.format("Cannot find node with name '%s' in diagram", name))), type, name, desc, lane);
    }

    private Lane getLaneByRoleName(ProcessDiagram diagram, String roleName) {
        return diagram.getPools()
                .stream()
                .map(Pool::getLanes)
                .flatMap(Collection::stream)
                .filter(lane -> roleName.equals(lane.getRole().getName()))
                .findFirst()
                .orElseThrow(() -> new AssertionError(String.format("Unable to find lane with role '%s'", roleName)));
    }
}
