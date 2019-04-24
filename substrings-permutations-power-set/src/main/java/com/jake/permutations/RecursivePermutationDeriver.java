package com.jake.permutations;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of {@link PermutationDeriver} that finds all permutations of k-length substrings by systematically
 * selecting characters for each "slot" in the k-length substring.
 */
public class RecursivePermutationDeriver implements PermutationDeriver {

	// *************************************
	// * Implementations
	// *************************************

	@Override
	public Set<String> derive(String s, int k) {
		Set<Character> chars = s.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.toSet());

		return derivePermutationsStartingWith("", chars, k);
	}

	// *************************************
	// * Helpers
	// *************************************

	private Set<String> derivePermutationsStartingWith(String prefix, Set<Character> remainingChars, int k) {
		if (prefix.length() == k) {
			return Set.of(prefix);
		}

		Set<String> permutations = new HashSet<String>();

		for (Character c : remainingChars) {
			Set<Character> otherChars = new HashSet<>(remainingChars);
			otherChars.remove(c);

			permutations.addAll(derivePermutationsStartingWith(prefix + c, otherChars, k));
		}

		return permutations;
	}
}
