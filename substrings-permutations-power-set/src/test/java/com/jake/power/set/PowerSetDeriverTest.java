package com.jake.power.set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PowerSetDeriverTest {

	// *************************************
	// * Members
	// *************************************

	// TODO [Apr 24, 2019] Initialize
	private PowerSetDeriver deriver;

	// *************************************
	// * Tests
	// *************************************

	@ParameterizedTest
	@MethodSource("provideTestCases")
	<T> void testDeriver(Set<T> input, Set<Set<T>> expectedOutput) {
		Set<Set<T>> output = deriver.derive(input);
		assertEquals(expectedOutput, output);
	}

	// *************************************
	// * Cases
	// *************************************

	static Stream<Arguments> provideTestCases() {
		//@formatter:off
		return Stream.of(
			// Empty set
			Arguments.of(Set.of(), Set.of(Set.of())),
			// Set containing a single element
			Arguments.of(Set.of(1), Set.of(Set.of(), Set.of(1))),
			// Multiple elements
			Arguments.of(Set.of(1, 2, 3), Set.of(Set.of(), Set.of(1), Set.of(2), Set.of(3), Set.of(1, 2), Set.of(1, 3), Set.of(2, 3), Set.of(1, 2, 3)))
		);
		//@formatter:on
	}
}
