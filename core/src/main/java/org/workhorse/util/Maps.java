package org.workhorse.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for working with maps.
 *
 * @author Brennan Spies
 */
public class Maps {
    /**
     * Creates a new {@link HashMap} with the appropriate key/value types.
     * @return The new hash map
     */
    public static <K,V> Map<K,V> newHashMap() {
        return new HashMap<K,V>();
    }
}
