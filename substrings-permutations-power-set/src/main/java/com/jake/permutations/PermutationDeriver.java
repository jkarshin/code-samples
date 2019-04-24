package com.jake.permutations;

import java.util.Set;

/**
 * Interface for solutions to the following problem:<br>
 * <br>
 * Derive all length <code>k</code> substrings of permutations of a given string <code>s</code>. <code>k</code> will be
 * in the range [0, <code>s.length</code>]. <code>s</code> will have no duplicate characters.
 */
public interface PermutationDeriver {
	Set<String> derive(String s, int k);
}
