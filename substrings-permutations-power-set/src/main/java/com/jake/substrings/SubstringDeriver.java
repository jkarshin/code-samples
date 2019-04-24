package com.jake.substrings;

import java.util.Set;

/**
 * Interface for solutions to the following problem:<br>
 * <br>
 * Derive all substrings of a given string <code>s</code>, including the empty string.
 */
public interface SubstringDeriver {
	Set<String> derive(String s);
}
