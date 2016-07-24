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
package org.workhorse.event;

import org.workhorse.graph.SequenceFlow;
import org.workhorse.graph.exec.ActivityNode;

/**
 * Marks an event that can be attached to an {@link org.workhorse.activity.Activity Activity}
 * and enable alternative flow.
 *
 * @author Brennan Spies
 */
public interface Attachable 
{
	/**
	 * Returns the source to which this {@link Event} is attached.
	 * @return The source ActivityNode
	 */
	ActivityNode getAttachSource();
	
	/**
	 * The transition of the exception flow, if any.
	 * @return The target of the event
	 */
	SequenceFlow getFlow();
}
