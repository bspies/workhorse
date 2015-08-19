package org.workhorse.flow;

import org.workhorse.exec.Execution;

/**
 * @author Brennan Spies
 *
 * <p></p>
 */
public interface NewThreadListener {
    /**
     * Listener method for a new thread in the control flow.
     * @param execution
     */
    public void onNewThread(Execution execution);
}
