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
package org.workhorse.exec;

import org.workhorse.Element;
import org.workhorse.graph.node.Node;

/**
 * Captures the position of a paused {@link Execution}, so that it
 * can be resumed later.
 *
 * @author Brennan Spies
 */
public class Continuation extends Element
{
	private Execution execution;
    private String nodeId;
	private Phase phase;

    //For JPA only
    public Continuation() {}
	
	public Continuation(Execution execution, Node node, Phase phase) {
        this.execution = execution;
		this.nodeId = node.getId();
		this.phase = phase;
	}
	
	/**
	 * Returns the {@code Node} where the {@code Execution} will
	 * resume.
	 * @return The node where the execution will resume
	 */
	public String getNodeId() {
		return nodeId;
	}
	
	/**
	 * Returns the phase in which the {@code Execution} will
	 * resume.
	 * @return The phase of node traversal
	 */
	public Phase getPhase() {
		return phase;
	}

    /**
     * Returns the execution that this continuation is related to.
     * @return The execution
     */
    public Execution getExecution() {
        return execution;
    }
}
