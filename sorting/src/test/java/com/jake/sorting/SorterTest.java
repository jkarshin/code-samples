package com.jake.sorting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SorterTest {

	// *************************************
	// * Tests
	// *************************************

	@ParameterizedTest
	@MethodSource("provideSorterAndTestCases")
	<T extends Comparable<T>> void testSorter(Sorter<T> sorter, List<T> input, List<T> expectedOutput) {
		sorter.sort(input);
		assertEquals(expectedOutput, input);
	}

	// *************************************
	// * Test Cases
	// *************************************

	@SuppressWarnings("unchecked")
	static Stream<Arguments> provideSorterAndTestCases() {
		// TODO [Apr 24, 2019] Add implementations here
		//@formatter:off
		return Stream.of(new MergeSorter<Integer>())
				.flatMap(sorter ->
						// Need to wrap the input list, since List.of(...) yields an ImmutableList
						provideTestCases().map(args -> Arguments.of(sorter, new ArrayList<>((List<Integer>) args.get()[0]), args.get()[1])));
		//@formatter:on
	}

	private static Stream<Arguments> provideTestCases() {
		//@formatter:off
		return Stream.of(
			// Empty List
			Arguments.of(List.of(), List.of()),
			// Singleton List
			Arguments.of(List.of(42), List.of(42)),
			// List containing only duplicate elements
			Arguments.of(List.of(42, 42, 42), List.of(42, 42, 42)),
			// List already in order, no duplicates
			Arguments.of(List.of(1, 2, 3), List.of(1, 2, 3)),
			// List already in order, with duplicates
			Arguments.of(List.of(1, 1, 2, 2, 3), List.of(1, 1, 2, 2, 3)),
			// List in reverse order, no duplicates
			Arguments.of(List.of(3, 2, 1), List.of(1, 2, 3)),
			// List in reverse order, with duplicates
			Arguments.of(List.of(3, 3, 2, 2, 1), List.of(1, 2, 2, 3, 3)),
			// List with no order, no duplicates
			Arguments.of(List.of(3, 2, 4, 1), List.of(1, 2, 3, 4)),
			// List with no order, with duplicates
			Arguments.of(List.of(3, 2, 4, 1, 3, 5, 1, 1), List.of(1, 1, 1, 2, 3, 3, 4, 5))
		);
		//@formatter:on
	}
}
