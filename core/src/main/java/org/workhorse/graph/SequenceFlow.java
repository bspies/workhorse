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
package org.workhorse.graph;

import java.util.Collection;

import org.workhorse.Element;
import org.workhorse.exec.Execution;
import org.workhorse.expr.Condition;
import org.workhorse.graph.node.Node;
import org.workhorse.graph.node.SourceNode;
import org.workhorse.graph.node.TargetNode;

/**
 * @author Brennan Spies  
 * <p>The graph element (represented as an edge between two vertices) that
 * is responsible for carrying the <code>Execution</code> from one node to the next.</p>
 */
public class SequenceFlow extends Element
{
	private SourceNode sourceNode;
	private TargetNode targetNode;
	private String name;
	private boolean defaultTransition;
	private Condition condition;
	private Collection<TransitionListener> listeners;
	
	public SequenceFlow() {}
	
	/**
	 * Creates a transition with a source and target
	 * @param sourceNode The transition source
	 * @param targetNode The transition target
	 */
	public SequenceFlow(SourceNode sourceNode, TargetNode targetNode) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
	}
	
	/**
	 * Adds a listener to this <code></code>.
	 * @param listener The listener to add
	 */
	public void addListener(TransitionListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Returns the name of the transition.
	 * @return The transition name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the source of this transition.
	 * @return The source
	 */
	public Node getSource() {
		return sourceNode;
	}
	
	/**
	 * Returns the target of this transition.
	 * @return The target node
	 */
	public TargetNode getTarget() {
		return targetNode;
	}
	
	/**
	 * Returns the condition associated with this transition, i.e. the transition can only fire if the condition is met.
	 * @return  the condition
	 */
	public Condition getCondition() {
		return condition;
	}
	
	/**
	 * Returns true if this transition is a default transition; the condition will be ignored if this is the case.
	 * @return True if this is a default transition
	 */
	public boolean isDefaultTransition() {
		return defaultTransition;
	}
	
	/**
	 * Sets the default transition flag.
	 * @param isDefault  True if this transition is the default
	 * @uml.property  name="defaultTransition"
	 */
	public void setDefaultTransition(boolean isDefault) {
		this.defaultTransition = isDefault;
	}

	/**
	 * Sets the condition associated with this transition.
	 * @param condition  the condition to set
	 */
	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	/**
	 * Fires this transition, sending the execution
	 * to the next node in the graph.
	 * @param execution The process execution
	 */
	public void fire(Execution execution) {
		for(TransitionListener listener : listeners) {
			listener.onTransition(this, execution);
		}
		targetNode.perform(this, execution);
	}
}
