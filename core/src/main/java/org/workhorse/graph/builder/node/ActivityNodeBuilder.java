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
package org.workhorse.graph.builder.node;

import org.workhorse.activity.Activity;
import org.workhorse.graph.exec.ActivityNode;
import org.workhorse.type.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Base builder for activity nodes.
 *
 * @author Brennan Spies
 */
public abstract class ActivityNodeBuilder<N extends ActivityNode,B extends ActivityNodeBuilder<N,B>>
        extends BaseNodeBuilder<N,B> {

    private Collection<Parameter<?>> inputParams = new ArrayList<>(),
                                     outputParams = new ArrayList<>();

    public B withInput(Parameter<?>... params) {
        inputParams.addAll(Arrays.asList(params));
        return self();
    }

    public B withOutput(Parameter<?>... params) {
        outputParams.addAll(Arrays.asList(params));
        return self();
    }

/*    public B withAttachedEvent(Event event) {
        return self();
    }*/

    /**
     * Adds the input and output parameters to the activity node.
     * @param activityNode The activity node
     */
    protected void addParameters(ActivityNode<? extends Activity> activityNode) {
        inputParams.forEach(activityNode::addInput);
        outputParams.forEach(activityNode::addOutput);
    }
}
