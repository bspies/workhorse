package org.workhorse.event;

import com.google.inject.TypeLiteral;
import org.workhorse.graph.node.Node;

/**
 * Represents an escalation event.
 *
 * @author Brennan Spies
 */
public class Escalation extends BaseEvent implements ThrownEvent<Escalation>
{
    public static final EventType<Escalation> type =
            new EventType<Escalation>(new TypeLiteral<Escalation>(){});

    /**
     * Creates the event with a reference to its source.
     * @param source The event's source node
     */
    public Escalation(Node source) {
        super(source);
    }

    /**
     * {@inheritDoc}
     */
    @Override public EventType<Escalation> getType() {
        return type;
    }
}
