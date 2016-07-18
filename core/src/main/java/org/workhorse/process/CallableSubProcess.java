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
package org.workhorse.process;

import org.workhorse.activity.Activity;
import org.workhorse.exec.Execution;
import org.workhorse.exec.RunState;
import org.workhorse.exec.ctx.*;
import org.workhorse.type.val.Value;

import java.util.UUID;

/**
 * Implementation of {@link SubProcess} that can be called by a process.
 *
 * @author Brennan Spies
 */
public class CallableSubProcess extends BaseContainer implements Activity, SubProcess
{
    private boolean transactional;
    private VersionedContext context;

    /**
     * Creates the subprocess.
     * @param id The instance id
     */
    public CallableSubProcess(UUID id, VersionedContext context) {
        super(id);
        this.context = context;
    }

    /** {@inheritDoc} */
    @Override public Context getContext() {
        return ReadOnlyContext.wrap(context);
    }

    /** {@inheritDoc} */
    @Override public void end() {

    }

    /** {@inheritDoc} */
    @Override public void cancel() {

    }

    /** {@inheritDoc} */
    @Override public boolean isTransactional() {
        return transactional;
    }

    /** Sets the transactional flag */
    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }

    /** {@inheritDoc} */
    @Override
    public RunState perform(Execution execution) {
        return null; //todo implement
    }

    @Override
    public ReadValues getReadParentValues() {
        return null;  //todo implement
    }

    @Override
    protected void importValues(Iterable<Value<?>> inputValues, ReadValues snapshotValues) {
        inputValues.forEach(value ->
                context.createOrUpdateWritable(value, snapshotValues::getSnapshot));
    }
}
