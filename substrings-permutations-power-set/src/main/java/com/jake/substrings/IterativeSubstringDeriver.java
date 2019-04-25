package com.jake.substrings;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link SubstringDeriver} that iterates over all substrings in the input String. First, the
 * substrings starting at index 0 are derived, followed by the substrings starting at index 1, and so on.
 */
public class IterativeSubstringDeriver implements SubstringDeriver {

    // *************************************
    // * Implementations
    // *************************************

    @Override
    public Set<String> derive(String s) {
        Set<String> substrings = new HashSet<>();
        substrings.add("");

        for (int i = 0; i < s.length(); ++i) {
            for (int j = i + 1; j <= s.length(); ++j) {
                substrings.add(s.substring(i, j));
            }
        }

        return substrings;
    }
}
