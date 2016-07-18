/*
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
package org.workhorse.flow;

import org.workhorse.exec.Execution;
import org.workhorse.exec.RunResult;
import org.workhorse.exec.RunState;
import org.workhorse.graph.Diagram;
import org.workhorse.graph.SequenceFlow;
import org.workhorse.graph.exec.ActivityNode;
import org.workhorse.graph.event.EventNode;
import org.workhorse.graph.Node;

import java.util.Collection;

import static org.workhorse.exec.RunState.*;

/**
 * Default implementation of {@link Controller}.
 *
 * @author Brennan Spies
 */
public class ExecutionController implements Controller {

    private final Diagram diagram;

    /**
     * Default constructor, takes the process diagram.
     * @param diagram The process diagram
     */
    public ExecutionController(Diagram diagram) {
        this.diagram = diagram;
    }

    /** {@inheritDoc} */
    @Override
    public RunResult run(Node startNode, Execution execution) {
        RunState state = RUNNING;
        Node currentNode = startNode;
        while(state == RUNNING){
            state = doNode(currentNode, execution);
            if(state == RUNNING) {
                Collection<SequenceFlow> outgoing =
                        diagram.getOutgoingOf(currentNode, execution.getCurrentContext());
                switch (outgoing.size()) {
                    case 0:
                        //implicit completion
                        state = TERMINATED; break;
                    case 1:
                        currentNode = outgoing.iterator().next().getTarget(); break;
                    default:
                        // > 1 outgoing transitions
                        //todo implement split
                }
            } else if(state == ERROR) {
                //todo handle error flow
            }
        }
        return state==PAUSED ? RunResult.paused(currentNode) :
                state==ERROR ? RunResult.error(currentNode):
                        RunResult.terminated();
    }

    private RunState doNode(Node node, Execution execution) {
        if(node instanceof ActivityNode) {
            return execution.visit((ActivityNode) node);
        }
        else if(node instanceof EventNode) {
            return execution.visit((EventNode) node);
        }
        else {  //control node or gateway
            //todo handle gateway
        }
        return RUNNING;
    }
}
