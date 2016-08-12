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
package org.workhorse.graph.builder.container;

import org.workhorse.actor.Actor;
import org.workhorse.actor.role.Role;
import org.workhorse.graph.*;
import org.workhorse.graph.builder.*;
import org.workhorse.id.IdGenerator;
import org.workhorse.id.Identifiable;
import org.workhorse.util.Builder;
import org.workhorse.util.Version;
import org.workhorse.util.VoidFunction;
import org.workhorse.validation.Required;

import javax.inject.Inject;
import java.util.*;

/**
 * Builder for {@link ProcessDiagram} instances.
 *
 * @author Brennan Spies
 */
public class ProcessDiagramBuilder extends BaseBuilder
        implements Builder<ProcessDiagram>, DiagramBuilder {

    @Required private String processName;
    @Required private Version version;
    private IdGenerator<String> idGenerator;
    private BuilderState builderState = new BuilderState();

    /**
     * Creates the diagram builder.
     * @param idGenerator The id generator
     */
    @Inject
    public ProcessDiagramBuilder(IdGenerator<String> idGenerator) {
        this.idGenerator = idGenerator;
    }

    /**
     * Sets the process name on the diagram.
     * @param processName The process name
     * @return The diagram builder
     */
    public ProcessDiagramBuilder withProcessName(String processName) {
        this.processName = processName;
        return this;
    }

    /**
     * Sets the diagram version.
     * @param version The version
     * @return The diagram builder
     */
    public ProcessDiagramBuilder withVersion(Version version) {
        this.version = version;
        return this;
    }

    /**
     * Adds a participant to the process.
     * @param participant The participant to add
     * @return The participant builder
     */
    public ParticipantBuilder withParticipant(Actor participant) {
        return withParticipant(ExistingObject.wrap(participant));
    }

    /**
     * Adds a participant to the process.
     * @param actorBuilder The builder for the participant
     * @return The participant builder
     */
    public ParticipantBuilder withParticipant(ContextualBuilder<? extends Actor> actorBuilder) {
        return new ParticipantBuilder(this, actorBuilder);
    }

    /**
     * Builds the final {@code ProcessDiagram}.
     * @return The diagram
     */
    @Override public ProcessDiagram build() {
        runValidation();
        WorkflowDiagram diagram = new WorkflowDiagram(processName, version);
        BuilderContext ctx = new InternalBuilderContext();
        ctx.setParent(diagram);
        builderState.getPools().forEach(poolBuilder -> diagram.addPool(ctx.build(poolBuilder)));
        builderState.getNodes().forEach(nodeBuilder -> diagram.addNode(ctx.build(nodeBuilder)));
        builderState.getFlows().forEach(flowBuilder -> diagram.addFlow(ctx.build(flowBuilder)));
        return diagram;
    }

    ////////////////////////////////////////////////
    // Package internal methods
    ////////////////////////////////////////////////
    /**
     * Add a pool to the build state.
     * @param poolBuilder The pool builder
     */
    public void addPool(ContextualBuilder<Pool> poolBuilder) {
        builderState.addPool(poolBuilder);
    }

    /**
     * Adds a node to the build state.
     * @param nodeBuilder The node builder
     */
    public void addNode(ContextualBuilder<? extends Node> nodeBuilder) {
        builderState.addNode(nodeBuilder);
    }

    /**
     * Adds a sequence flow to the build state.
     * @param sequenceFlow The sequence flow builder
     */
    public void addFlow(ContextualBuilder<SequenceFlow> sequenceFlow) {
        builderState.addFlow(sequenceFlow);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helper classes
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Internal implementation of {@link BuilderContext}.
     */
    private class InternalBuilderContext implements BuilderContext {

        //the current parent
        private Diagram parent;
        private Map<String,Node> builtNodes = new HashMap<>();

        @Override
        public String generateIdFor(Class<? extends Identifiable<String>> type) {
            return String.format("%s:%s", type.getSimpleName(), idGenerator.generate());
        }

        @Override
        public Diagram getParent() {
            return parent;
        }

        @Override
        public void setParent(Diagram parent) {
            this.parent = parent;
        }

        @Override
        public <T> T build(ContextualBuilder<T> builder) {
            T instance = builder.build(this);
            builderState.registerBuiltObject(builder, instance);
            return instance;
        }

        @Override
        public <T> Optional<T> getBuiltObject(ContextualBuilder<T> builder) {
            return builderState.getBuiltObject(builder);
        }

        @Override
        public <T extends Identifiable<String>> Optional<T> getBuiltObjectById(Class<T> type, String id) {
            return builderState.getBuiltObject(type, id);
        }

        @Override
        public Iterable<Node> getNodes() {
            return builtNodes.values();
        }
    }

    /**
     * Intermediate builder for setting the participant (i.e. pool)
     * in the process.
     */
    public static class ParticipantBuilder {

        private PoolBuilder poolBuilder;
        private ProcessDiagramBuilder parent;

        /**
         * Creates a participant builder.
         * @param parent The parent diagram builder
         * @param participant The participant builder
         */
        ParticipantBuilder(ProcessDiagramBuilder parent, ContextualBuilder<? extends Actor> participant) {
            this.parent = parent;
            this.poolBuilder = new PoolBuilder().withParticipant(participant);
            parent.addPool(poolBuilder);
        }

        /**
         * Creates a swim lane for the participant with the given role.
         * @param role The role
         * @param pathFunction The path building function
         * @return The role execution builder
         */
        public MultiOptionBuilder inRole(Role role, VoidFunction<PathBuilder> pathFunction) {
            return inRole(ExistingObject.wrap(role), pathFunction);
        }

        /**
         * Creates a swim lane for the participant with the given role, and
         * builds an execution path in it.
         * @param roleBuilder The builder for the role
         * @param pathFunction The path building function
         * @return The intermediate builder
         */
        public MultiOptionBuilder inRole(ContextualBuilder<Role> roleBuilder,
                                         VoidFunction<PathBuilder> pathFunction) {
            LaneBuilder laneBuilder = new LaneBuilder(roleBuilder);
            return inLane(laneBuilder, pathFunction);
        }

        /**
         * Builds an execution path in the given swim lane.
         * @param lane The swim lane
         * @param pathFunction The path building function
         * @return The intermediate builder
         */
        public MultiOptionBuilder inLane(Lane lane, VoidFunction<PathBuilder> pathFunction) {
            return inLane(ExistingObject.wrap(lane), pathFunction);
        }

        /**
         * Builds an execution path in the given swim lane.
         * @param laneBuilder The lane builder
         * @param pathFunction The path building function
         * @return The intermediate builder
         */
        public MultiOptionBuilder inLane(ContextualBuilder<Lane> laneBuilder,
                                         VoidFunction<PathBuilder> pathFunction) {
            poolBuilder.withLane(laneBuilder);
            PathBuilder pathBuilder = new PathBuilder(parent, laneBuilder);
            pathFunction.apply(pathBuilder);
            //parent.addPath(pathBuilder);
            return new MultiOptionBuilder(parent, this);
        }
    }

    /**
     * Intermediate builder which can either start building
     * another participant, another swim lane, or complete building
     * by calling {@link #build()}.
     *
     * @author Brennan Spies
     */
    public static class MultiOptionBuilder {

        private ProcessDiagramBuilder parent;
        private ParticipantBuilder participantBuilder;

        MultiOptionBuilder(ProcessDiagramBuilder parent, ParticipantBuilder participantBuilder) {
            this.parent = parent;
            this.participantBuilder = participantBuilder;
        }

        /**
         * Adds a participant to the process.
         * @param participant The participant to add
         * @return The participant builder
         */
        public ParticipantBuilder withParticipant(Actor participant) {
            return parent.withParticipant(participant);
        }

        /**
         * Adds a participant to the process.
         * @param actorBuilder The builder for the participant
         * @return The participant builder
         */
        public ParticipantBuilder withParticipant(ContextualBuilder<? extends Actor> actorBuilder) {
            return parent.withParticipant(actorBuilder);
        }

        /**
         * Delegates building to parent.
         */
        public ProcessDiagram build() {
            return parent.build();
        }

        /**
         * Creates a swim lane for the participant with the given role.
         * @param role The role
         * @param pathFunction The path building function
         * @return The role execution builder
         */
        public MultiOptionBuilder inRole(Role role, VoidFunction<PathBuilder> pathFunction) {
            return participantBuilder.inRole(role, pathFunction);
        }

        /**
         * Creates a swim lane for the participant with the given role.
         * @param roleBuilder The builder for the role
         * @param pathFunction The path building function
         * @return The role execution builder
         */
        public MultiOptionBuilder inRole(ContextualBuilder<Role> roleBuilder,
                                         VoidFunction<PathBuilder> pathFunction) {
            return participantBuilder.inRole(roleBuilder, pathFunction);
        }

        /**
         * Builds an execution path in the given swim lane.
         * @param lane The swim lane
         * @param pathFunction The path building function
         * @return The intermediate builder
         */
        public MultiOptionBuilder inLane(Lane lane, VoidFunction<PathBuilder> pathFunction) {
            return participantBuilder.inLane(lane, pathFunction);
        }

        /**
         * Builds an execution path in the given swim lane.
         * @param laneBuilder The lane builder
         * @param pathFunction The path building function
         * @return The intermediate builder
         */
        public MultiOptionBuilder inLane(ContextualBuilder<Lane> laneBuilder,
                                         VoidFunction<PathBuilder> pathFunction) {
            return participantBuilder.inLane(laneBuilder, pathFunction);
        }
    }
}
