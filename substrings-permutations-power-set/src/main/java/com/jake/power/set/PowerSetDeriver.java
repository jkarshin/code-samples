package com.jake.power.set;

import java.util.Set;

/**
 * Interface for solutions to the following problem:<br>
 * <br>
 * Derive all subsets of a given set (including the empty set and the input set itself).
 */
public interface PowerSetDeriver {
	<T> Set<Set<T>> derive(Set<T> set);
}
