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
package org.workhorse.exec;

/**
 * Enumeration to represent the current run state
 * of the {@link Execution}.
 *
 * @author Brennan Spies
 */
public enum RunState {
    //execution running
    RUNNING,
    //execution is paused
    PAUSED,
    //execution has terminated, normally or otherwise
    TERMINATED,
    //execution has hit an unhandled error
    ERROR
}
