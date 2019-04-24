package com.jake.bst.to.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.jake.bst.to.list.pojo.DoublyLinkedList;
import com.jake.bst.to.list.pojo.Node;

public class BSTConverterTest {

	// *************************************
	// * Members
	// *************************************

	// TODO [Apr 24, 2019] Initialize
	private BSTConverter converter;

	// *************************************
	// * Test + Helpers
	// *************************************

	@ParameterizedTest
	@MethodSource("provideTestCases")
	<T> void testConverter(Node<T> root) {
		// TODO [Apr 24, 2019] May not need DoublyLinkedList class; head of the list may be sufficient
		DoublyLinkedList<T> list = converter.convert(root);

		Node<T> extraListNode = assertConsistentValues(root, list.getHead());
		assertNull(extraListNode, "List contains more nodes than the tree or contains a loop.");
	}

	// TODO [Apr 24, 2019] may not need
	private <T> void assertNoCyclesInList(Node<T> head, Function<Node<T>, Node<T>> next) {
		/*
		 * If there are no cycles, we will eventually reach the tail (a node whose nextNode is null) by iterating over
		 * the list. If there is a cycle, we would iterate forever. To detect this, use two iterators, one moving faster
		 * than the other. If the faster iterator catches the slower iterator, we know there is a cycle.
		 */

		Function<Node<T>, Node<T>> fastNext = next.andThen(next);

		// This could be expressed as a for-loop, but it is broken apart for readability
		Node<T> iterSlow = head;
		Node<T> iterFast = next.apply(head);
		while (iterFast != null && next.apply(iterFast) != null) {
			assertTrue(iterFast != iterSlow, "Cycle detected in list.");

			iterSlow = next.apply(iterSlow);
			iterFast = fastNext.apply(iterFast);
		}
	}

	/**
	 * Performs an in-order traversal of the BST while also iterating (forward) over the list. In addition to asserting
	 * that the values are consistent, this method also asserts that each "previous" link in the list is correct.<br>
	 * <br>
	 * This method is tolerant of loops in the list but not in the BST. This method will catch a list containing fewer
	 * nodes than the BST, but not vice versa. In order to catch a list with more nodes (or a list with cycles), the
	 * return value from the original call must be asserted to be null. (A non-null return value indicates that the list
	 * contains more nodes than the tree or that the list has cycles.)
	 */
	private <T> Node<T> assertConsistentValues(Node<T> root, Node<T> listNode) {
		if (root == null) {
			return listNode;
		}
		assertNotNull(listNode, "List contains fewer nodes than the BST.");

		// Recurse left
		listNode = assertConsistentValues(root.node1, listNode);

		// Validate the current node
		assertEquals(root.value, listNode.value, "BST and list are inconsistent. Values do not match at node.");
		assertTrue(listNode.node2 == null || listNode == listNode.node2.node1,
				"Incorrect previous node link detected in list.");
		listNode = listNode.node2;

		// Recurse right
		return assertConsistentValues(root.node2, listNode);
	}

	// *************************************
	// * Cases
	// *************************************

	Stream<Arguments> provideTestCases() {
		//@formatter:off
		return Stream.of(
		    // TODO [Apr 24, 2019] Add test cases
		);
		//@formatter:on
	}
}
