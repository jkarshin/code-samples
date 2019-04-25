package com.jake.bst.to.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.jake.bst.to.list.pojo.Node;

public class BSTConverterTest {

    // *************************************
    // * Members
    // *************************************

    private BSTConverter converter = new InPlaceBSTConverter();

    // *************************************
    // * Test + Helpers
    // *************************************

    @ParameterizedTest
    @MethodSource("provideTestCases")
    <T> void testConverter(Node<T> root) {
        /*
         * Before doing the conversion, make a copy of the original BST so that we have something to compare the output
         * list to. (If the BSTConverter implementation is in-place, the original BST will no longer be a BST.)
         */
        Node<T> rootCopy = deepCopyBST(root);

        Node<T> head = converter.convert(root);

        Node<T> extraListNode = assertConsistentValues(rootCopy, head);
        assertNull(extraListNode, "List contains more nodes than the tree or contains a loop.");
    }

    private <T> Node<T> deepCopyBST(Node<T> root) {
        Node<T> leftCopy = root.node1 != null ? deepCopyBST(root.node1) : null;
        Node<T> rightCopy = root.node2 != null ? deepCopyBST(root.node2) : null;

        return new Node<>(root.value, leftCopy, rightCopy);
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

    static Stream<Node<?>> provideTestCases() {
        //@formatter:off
		return Stream.of(
		    // Tree with a single node
			new Node<>(42),
			/*
			 * Tree with only left children
			 *      3
			 *     /
			 *    2
			 *   /
			 *  1
			 */
			new Node<>(3,
				new Node<>(2,
					new Node<>(1),
					null
				),
				null
			),
			/*
			 * Tree with only right children
			 *      1
			 *       \
			 *        2
			 *         \
			 *          3
			 */
			new Node<>(1,
				null,
				new Node<>(2,
					null,
					new Node<>(3)
				)
			),
			/*
			 * Tree with alternating left/right children
			 * 
			 *        3
			 *      /   \
			 *     1     5
			 *      \   /
			 *       2 4
			 */
			new Node<>(3, 
				new Node<>(1, 
					null,
					new Node<>(2)
				),
				new Node<>(5,
					new Node<>(4),
					null
				)
			),
			/*
			 * Full tree
			 * 
			 *       4
			 *     /   \
			 *    2     6
			 *   / \   / \
			 *  1   3 5   7
			 */
			new Node<>(4,
				new Node<>(2,
					new Node<>(1),
					new Node<>(3)
				),
				new Node<>(6,
					new Node<>(5),
					new Node<>(7)
				)
			),
			/*
			 * Imbalanced tree
			 * 
			 *       2
			 *     /   \
			 *    1     3
			 *           \
			 *            7
			 *           /
			 *          5
			 *         / \
			 *        4   6
			 */
			new Node<>(2,
				new Node<>(1),
				new Node<>(3,
					null,
					new Node<>(7,
						new Node<>(5,
							new Node<>(4),
							new Node<>(6)
						),
						null
					)
				)
			)
		);
		//@formatter:on
    }
}
