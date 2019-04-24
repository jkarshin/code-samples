package com.jake.bst.to.list;

import com.jake.bst.to.list.pojo.DoublyLinkedList;
import com.jake.bst.to.list.pojo.Node;

/**
 * Interface for solutions to the following problem (paraphrased from Gayle Laakmann McDowell's <i>Cracking the Coding
 * Interview</i>, question 17.12):<br>
 * <br>
 * Convert a given binary search tree into a doubly linked list. The order of the node values must be preserved.
 */
public interface BSTConverter {
	<T> DoublyLinkedList<T> convert(Node<T> root);
}
