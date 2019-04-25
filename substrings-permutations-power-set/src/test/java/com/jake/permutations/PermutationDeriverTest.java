package com.jake.permutations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PermutationDeriverTest {

    // *************************************
    // * Members
    // *************************************

    private PermutationDeriver deriver = new RecursivePermutationDeriver();

    // *************************************
    // * Tests
    // *************************************

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testDeriver(String s, int k, Set<String> expectedPermutations) {
        Set<String> results = deriver.derive(s, k);
        assertEquals(expectedPermutations, results);
    }

    // *************************************
    // * Cases
    // *************************************

    static Stream<Arguments> provideTestCases() {
        //@formatter:off
		return Stream.of(
			// Empty String
			Arguments.of("", 0, Set.of("")),
			// Non-empty String, but 0-length permutations
			Arguments.of("abc", 0, Set.of("")),
			// Length 1 permutations
			Arguments.of("abc", 1, Set.of("a", "b", "c")),
			// Permutations of length s.length
			Arguments.of("abc", 3, Set.of("abc", "acb", "bac", "bca", "cab", "cba")),
			// Permutations of length < s.length
			Arguments.of("abc", 2, Set.of("ab", "ba", "ac", "ca", "bc", "cb"))
		);
		//@formatter:off
	}
}
