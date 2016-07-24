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
package org.workhorse.type.constraint;

import org.workhorse.type.var.Variable;

/**
 * Constrains the {@link Variable} to be within a range of values.
 *
 * @author Brennan Spies
 */
public class RangeConstraint<T extends Comparable<T>> implements Constraint<T>
{
    private MinimumConstraint minConstraint;
    private MaximumConstraint maxConstraint;

    private RangeConstraint(T minimum, boolean minInclusive, T maximum, boolean maxInclusive) {
       if(minimum==null && maximum==null) {
           throw new IllegalArgumentException("Range constraint must have a minimum and/or maximum value");
       } else if(minimum!=null) {
           this.minConstraint = new MinimumConstraint(minimum, minInclusive);
       } else {
           this.maxConstraint = new MaximumConstraint(maximum, maxInclusive);
       }
    }

    /*=================================================================================
        Factory methods
     ==================================================================================*/

    /**
     * Returns a range constraint that is constrained by a minimum inclusive value, i.e.
     * the validated value must be >= the {@code minimum} value.
     * @param minimum The minimum value
     * @return The range constraint
     */
    public static <T extends Comparable<T>> RangeConstraint<T> minInclusive(T minimum) {
       return new RangeConstraint<>(minimum, true, null, false);
    }

    /**
     * Returns a range constraint that is constrained by a minimum exclusive value, i.e.
     * the validated value must be > the {@code minimum} value.
     * @param minimum The minimum value
     * @return The range constraint
     */
    public static <T extends Comparable<T>> RangeConstraint<T> minExclusive(T minimum) {
        return new RangeConstraint<>(minimum, false, null, false);
    }

    /**
     * Returns a range constraint that is constrained by a maximum inclusive value, i.e.
     * the validated value must be <= the {@code maximum} value.
     * @param maximum The maximum value
     * @return The range constraint
     */
    public static <T extends Comparable<T>> RangeConstraint<T> maxInclusive(T maximum) {
        return new RangeConstraint<>(null, false, maximum, true);
    }

    /**
     * Returns a range constraint that is constrained by a maximum exclusive value, i.e.
     * the validated value must be < the {@code maximum} value.
     * @param maximum The maximum value
     * @return The range constraint
     */
    public static <T extends Comparable<T>> RangeConstraint<T> maxExclusive(T maximum) {
        return new RangeConstraint<>(null, false, maximum, false);
    }

    /**
     * Returns a range constraint that is constrained by a minimum and maximum inclusive values, i.e.
     * the validated value must be >= the {@code minimum} value and <= the {@code maximum} value.
     * @param min The minimum value
     * @param max The maximum value
     * @return The range constraint
     */
    public static <T extends Comparable<T>> RangeConstraint<T> betweenInclusive(T min, T max) {
        return new RangeConstraint<T>(min, true, max, true);
    }

    /**
     * Returns a range constraint that is constrained by a minimum and maximum inclusive values, i.e.
     * the validated value must be >= the {@code minimum} value and <= the {@code maximum} value.
     * @param min The minimum value
     * @param max The maximum value
     * @return The range constraint
     */
    public static <T extends Comparable<T>> RangeConstraint<T> betweenExclusive(T min, T max) {
        return new RangeConstraint<>(min, false, max, false);
    }

    /**
     * Returns a range constraint that is constrained by a minimum and maximum values, where either
     * the minimum or maximum can be inclusive or exclusive.
     * @param min The minimum value
     * @param minInclusive If true, minimum value is inclusive, if false exclusive
     * @param max The maximum value
     * @param maxInclusive If true, maximum value is inclusive, if false exclusive
     * @return The range constraint
     */
    public static <T extends Comparable<T>> RangeConstraint<T> between(T min, boolean minInclusive, T max, boolean maxInclusive) {
        return new RangeConstraint<>(min, minInclusive, max, maxInclusive);
    }

    /**
     * Returns true if range has minimum bound. Otherwise the implied constraint
     * is negative infinity or the minimum possible value of {@code T}.
     * @return True if range constraint has a minimum value
     */
    public boolean hasMinimum() { return minConstraint!=null; }

    /**
     * Returns true if range has a maximum bound. Otherwise the implied constraint
     * is positive infinity or the maximum possible value of {@code T}.
     * @return True if range constraint has a maximum value
     */
    public boolean hasMaximum() { return maxConstraint!=null; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(T value) {
        boolean isValid = true;
        if(hasMinimum()) {
            isValid &= minConstraint.isValid(value);
        }
        if(hasMaximum()) {
            isValid &= maxConstraint.isValid(value);
        }
        return isValid;
    }

    //constraint for minimum range bound
    private class MinimumConstraint implements Constraint<T>
    {
        final T minimum;
        final boolean inclusive;

        MinimumConstraint(T minimum, boolean inclusive) {
            this.minimum = minimum;
            this.inclusive = inclusive;
        }

        @Override
        public boolean isValid(T value) {
            int compare = value.compareTo(minimum);
            return inclusive ? compare>=0 : compare>0;
        }
    }

    //constraint for maximum range bound
    private class MaximumConstraint implements Constraint<T>
    {
        final T maximum;
        final boolean inclusive;

        MaximumConstraint(T maximum, boolean inclusive) {
           this.maximum = maximum;
           this.inclusive = inclusive;
        }

        @Override
        public boolean isValid(T value) {
            int compare = value.compareTo(maximum);
            return inclusive ? compare<=0 : compare<0;
        }
    }
}
