package com.jake.bst.to.list;

import com.jake.bst.to.list.pojo.Node;

/**
 * Implementation of {@link BSTConverter} that manipulates the input BST nodes directly; no additional data structures
 * are used.
 */
public class InPlaceBSTConverter implements BSTConverter {

	// *************************************
	// * Implementations
	// *************************************

	@Override
	public <T> Node<T> convert(Node<T> root) {
		/*
		 * The conversion helper will return the tail of the list. To get the head, we could traverse backwards from the
		 * tail, or we could fetch the lowest value node from the BST before we convert it.
		 * 
		 * Neither option will impact the overall runtime complexity, but fetching the lowest value from the BST will be
		 * better than or equivalent to traversing the entire list.
		 */
		Node<T> head = fetchLowestValueNode(root);

		convertHelper(root, null);

		return head;
	}

	// *************************************
	// * Helpers
	// *************************************

	private <T> Node<T> fetchLowestValueNode(Node<T> root) {
		return root.node1 == null ? root : fetchLowestValueNode(root.node1);
	}

	/**
	 * Performs an in-order traversal over the BST, keeping track of the previously visited node. At each step, the
	 * previous node and current node are set to point to one another.<br>
	 * <br>
	 * The lowest value node's 'previous' node and the highest value node's 'next' node are not altered by this method.
	 * These node references will remain null (since the lowest value node will have no left child and the highest value
	 * node will have no right child).<br>
	 * <br>
	 * Because we only adjust the 'previous' node (which has already been visited) and the reference to the
	 * previous/left node on the root node, our in-place assignments will not interfere with the in-order traversal.
	 */
	private <T> Node<T> convertHelper(Node<T> root, Node<T> previous) {
		if (root == null) {
			return previous;
		}

		// Recurse left
		previous = convertHelper(root.node1, previous);

		if (previous != null) {
			root.node1 = previous;
			previous.node2 = root;
		}
		previous = root;

		// Recurse right
		return convertHelper(root.node2, previous);
	}
}
