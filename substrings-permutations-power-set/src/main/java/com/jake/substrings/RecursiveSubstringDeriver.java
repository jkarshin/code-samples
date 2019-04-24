package com.jake.substrings;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of {@link SubstringDeriver} that is based on the following recursive logic:<br>
 * <br>
 * The substrings of String <code>s</code> starting at index <code>i</code> are defined by:<br>
 * <code>i == s.length</code> : the empty String<br>
 * <code>i < s.length</code> : the empty String and all substrings starting at index <code>i + 1</code> prefixed by the
 * character at <code>i</code><br>
 * <br>
 * All substrings can be obtained by union-ing the substrings starting at each index. To avoid repeated work, we obtain
 * the substrings starting at index 0, and accumulate the substrings starting at the other indices as we derive the
 * substrings starting at index 0.
 */
public class RecursiveSubstringDeriver implements SubstringDeriver {

	// *************************************
	// * Implementations
	// *************************************

	@Override
	public Set<String> derive(String s) {
		Set<String> substrings = new HashSet<>();
		deriveSubStringsStartingAt(0, s, substrings);
		return substrings;
	}

	// *************************************
	// * Helpers
	// *************************************

	private Set<String> deriveSubStringsStartingAt(int index, String s, Set<String> allSubstrings) {
		if (index == s.length()) {
			allSubstrings.add("");
			return Set.of("");
		}

		char c = s.charAt(index);

		Set<String> substringsAtIndex = deriveSubStringsStartingAt(index + 1, s, allSubstrings).stream()
				.map(substring -> c + substring)
				.collect(Collectors.toSet());

		allSubstrings.addAll(substringsAtIndex);
		substringsAtIndex.add("");

		return substringsAtIndex;
	}
}
