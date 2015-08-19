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
package org.workhorse.graph.node;

import org.workhorse.event.EventType;
import org.workhorse.event.ThrownEvent;
import org.workhorse.event.handler.Catcher;
import org.workhorse.exec.Block;
import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.graph.Graph;
import org.workhorse.graph.Lane;
import org.workhorse.graph.SequenceFlow;
import org.workhorse.graph.control.NodeControl;
import org.workhorse.sequence.Sequence;
import org.workhorse.sequence.SequenceBuilder;
import org.workhorse.sequence.Step;
import org.workhorse.sequence.impl.LinearSequence;
import org.workhorse.util.Maps;
import org.workhorse.var.Parameter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Base node for all nodes in a graph that contain workflow activities.
 *
 * @author Brennan Spies
 */
public abstract class ActivityNode extends IntermediateNode implements Block
{
	private Set<Parameter<?>> inputSet, outputSet;
	private NodeControl control;
	private Sequence processSteps;
	private CatcherMap catchers;
	
	/**
	 * Default constructor.
	 * @param parent The graph parent
	 * @param lane The lane to which the node belongs
	 */
	public ActivityNode(Graph parent, Lane lane) {
		super(parent, lane);
		inputSet = new HashSet<Parameter<?>>();
		outputSet = new HashSet<Parameter<?>>();
		processSteps = getSequence();
	}
	
	/**
	 * Sets a handler for a given event.
	 * @param catcher The handler to set
	 */
	public void setCatcher(Catcher<? extends ThrownEvent<?>> catcher) {
		if(catchers==null)
			catchers = new CatcherMap();
		catchers.put(catcher);
	}
	
	/**
	 * Returns the event catcher for the given event if it exists for
	 * this activity node.
	 * @param <E> The event type
	 * @param event The event to be matched
	 * @return The catcher, or null if none
	 */
	public <E extends ThrownEvent<E>> Catcher<E> getCatcher(E event) {
		if(catchers==null) return null;
		return catchers.get(event);
	}

	/**
	 * @see org.workhorse.exec.Block#getInputSet()
	 */
	public Set<Parameter<?>> getInputSet() {
		return inputSet;
	}

	/**
	 * @see org.workhorse.exec.Block#getOutputSet()
	 */
	public Set<Parameter<?>> getOutputSet() {
		return outputSet;
	}
	
	/**
	 * Returns any control associated with the node
	 * @return  The node control, or null if none
	 */
	public NodeControl getControl() {
		return control;
	}
	
	/**
	 * Sets a control on this node.
	 * @param control  The node control
	 */
	public void setControl(NodeControl control) {
		this.control = control;
	}
	
	/**
	 * @see org.workhorse.graph.node.TargetNode#perform(org.workhorse.graph.SequenceFlow, org.workhorse.exec.Execution)
	 */
	public void perform(SequenceFlow sequenceFlow, Execution execution) {
		try {
			processSteps.perform(execution);
		} catch (RuntimeException re) {
			handleUnexpected(execution, re);
		}
	}

	/**
	 * @see org.workhorse.graph.node.Node#reenter(org.workhorse.exec.Execution, Phase)
	 */
	public void reenter(Execution execution, Phase phase) {
		try {
			processSteps.reenter(phase, execution);
		} catch (RuntimeException re) {
			handleUnexpected(execution, re);
		}
	}
	
	/**
	 * Returns the process sequence.
     * @return The activity sequence
	 */
	protected Sequence getSequence() {
		SequenceBuilder sequenceBuilder = LinearSequence.newBuilder();
		sequenceBuilder.add(Phase.ENTER, new EntryStep());
		//if control, create a new builder for inner
		//sequence
		if(control!=null) {
			SequenceBuilder innerBuilder = control.getBuilder();
			addSteps(innerBuilder);
			//add inner control sequence to builder
			sequenceBuilder.add(innerBuilder.build());
		} else {
			addSteps(sequenceBuilder);
		}
		sequenceBuilder.add(Phase.LEAVE, new ExitStep());
		return sequenceBuilder.build();
	}
	
	/**
	 * Delegates the creation of the activity steps to concrete subclasses.
	 * @param sequenceBuilder The sequence builder
	 */
	protected abstract void addSteps(SequenceBuilder sequenceBuilder);
	
	/*
	 * Performs the post-process step: a callback on the execution.
	 */
	protected static class PostProcessStep implements Step 
	{
		private ActivityNode node;
		
		PostProcessStep(ActivityNode node) {
			this.node = node;
		}

		public boolean perform(Execution execution) {
			execution.postProcess(node);
			return !execution.isPaused();
		}
	}
	
	/*
	 * Performs the first step in the node, notifying the listeners.
	 */
	protected class EntryStep implements Step {
		public boolean perform(Execution execution) {
			onEntering(execution);
			return !execution.isPaused();
		}
	}
	
	/*
	 * Performs the last step in the node, determining which transitions
	 * should fire and firing them. Also notifies listeners.
	 */
	protected class ExitStep implements Step {
		public boolean perform(Execution execution) {
			onLeaving(execution);
			leave(execution);
			return true;
		}
	}
	
	/*
	 * Map that holds EventType->Catcher mappings.
	 */
	private static class CatcherMap 
	{
		private Map<EventType<?>,Catcher<?>> catchers;
		
		CatcherMap() {
			catchers = Maps.newHashMap();
		}
		
		/**
		 * Places a <code>Catcher</code> in the map.
		 * @param catcher The catcher to put
		 */
		void put(Catcher<?> catcher) {
			catchers.put(catcher.forType(), catcher);
		}
		
		@SuppressWarnings("unchecked")
		<E extends ThrownEvent<E>> Catcher<E> get(E event) {
			Catcher<E> catcher = (Catcher<E>)catchers.get(event.getType());
			if(catcher!=null && catcher.matches(event)) {
				return catcher;
			} else {
				return null;
			}
		}
	}
}
