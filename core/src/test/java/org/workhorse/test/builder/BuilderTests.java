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

import org.assertj.core.api.Condition;
import org.assertj.core.condition.AllOf;
import org.junit.Test;
import org.workhorse.actor.Actor;
import org.workhorse.actor.User;
import org.workhorse.actor.UserGroup;
import org.workhorse.graph.Lane;
import org.workhorse.graph.Node;
import org.workhorse.graph.Pool;
import org.workhorse.graph.ProcessDiagram;
import org.workhorse.graph.builder.container.ProcessDiagramBuilder;
import org.workhorse.graph.builder.actor.DepartmentBuilder;
import org.workhorse.graph.builder.actor.RoleBuilder;
import org.workhorse.graph.builder.actor.UserBuilder;
import org.workhorse.graph.builder.node.TaskNodeBuilder;
import org.workhorse.graph.exec.TaskNode;
import org.workhorse.test.util.TestIdGenerator;
import org.workhorse.util.Version;

import java.util.Collection;

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
    @Test public void testDiagramWithSingleUserRoleAndTask() {

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
        assertThat(processDiagram.getNodes()).hasSize(1);
        Collection<Pool> pools = processDiagram.getPools();
        assertThat(pools).as("Should be exactly one Pool").hasSize(1);

        Pool onlyPool = pools.iterator().next();
        Actor actor = onlyPool.getParticipant();
        assertThat(actor).isNotNull();
        assertThat(actor).as("Actor should be User").isInstanceOf(User.class);
        assertThat(actor.getDisplayName()).contains(firstName, lastName);
        assertThat(onlyPool.getLanes()).as("Should have exactly one Lane").hasSize(1);

        Lane onlyLane = onlyPool.getLanes().iterator().next();
        assertThat(onlyLane.getRole()).isNotNull();
        assertThat(onlyLane.getRole().getName()).isEqualTo(roleName);

        Node onlyNode = processDiagram.getNodes().iterator().next();
        assertThat(onlyNode).as("Node should be Task node").isInstanceOf(TaskNode.class);
        assertThat(onlyNode.getName()).isEqualTo(taskName);
        assertThat(onlyNode.getDescription()).isEqualTo(taskDesc);
        assertThat(onlyNode.getLane()).as("Node lane should not be null").isNotNull();
        assertThat(onlyNode.getLane().getRole()).as("Lane role should not be null").isNotNull();
        assertThat(onlyNode.getLane().getRole().getName()).isNotNull();
    }

    /**
     * Tests a diagram with multiple swim lanes and tasks, but no flows.
     */
    @Test public void testDiagramWithUserMultipleRolesAndTasksNoFlows() {
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
        processDiagram.getNodes()
                .forEach(node -> {
                    assertThat(node.getName())
                        .as("Node name should not be null").isNotNull();
                    assertThat(node.getLane())
                        .as(String.format("Node %s has no lane", node)).isNotNull();
                });

        Collection<Pool> pools = processDiagram.getPools();
        assertThat(pools).as("Should be exactly one Pool").hasSize(1);

        Pool onlyPool = pools.iterator().next();
        Actor actor = onlyPool.getParticipant();
        assertThat(actor).isNotNull();
        assertThat(actor).as("Actor should be UserGroup").isInstanceOf(UserGroup.class);
        assertThat(actor.getDisplayName()).isEqualTo(deptName);
        assertThat(onlyPool.getLanes()).as("Should have exactly 3 Lanes").hasSize(3);
    }
}
