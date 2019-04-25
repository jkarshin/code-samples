package com.jake.substrings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SubstringDeriverTest {

    // *************************************
    // * Tests
    // *************************************

    @ParameterizedTest
    @MethodSource("provideDeriverAndTestCases")
    void testDeriver(SubstringDeriver deriver, String input, Set<String> expectedSubstrings) {
        Set<String> output = deriver.derive(input);
        assertEquals(expectedSubstrings, output);
    }

    // *************************************
    // * Cases
    // *************************************

    static Stream<Arguments> provideDeriverAndTestCases() {
        //@formatter:off
        return Stream.of(new IterativeSubstringDeriver(), new RecursiveSubstringDeriver())
                .flatMap(deriver -> provideTestCases().map(args -> Arguments.of(deriver, args.get()[0], args.get()[1])));
        //@formatter:on
    }

    private static Stream<Arguments> provideTestCases() {
        //@formatter:off
        return Stream.of(
            // Empty String
            Arguments.of("",  Set.of("")),
            // Length 1 String
            Arguments.of("a", Set.of("", "a")),
            // String consisting only of duplicates
            Arguments.of("aaa", Set.of("", "a", "aa", "aaa")),
            // String consisting of distinct characters
            Arguments.of("abc", Set.of("", "a", "ab", "abc", "b", "bc", "c")),
            // String consisting of a mix of duplicates and distinct characters
            Arguments.of("ababc", Set.of("", "a", "ab", "aba", "abab", "ababc", "b", "ba", "bab", "babc", "abc", "bc", "c" ))
        );
        //@formatter:on
    }
}
