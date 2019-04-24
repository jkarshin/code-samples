package com.jake.bst.to.list.pojo;

/**
 * Helper class that groups a linked list's head and tail nodes.
 */
public class DoublyLinkedList<T> {

	// *************************************
	// * Members
	// *************************************

	private Node<T> head;
	private Node<T> tail;

	// *************************************
	// * Constructors
	// *************************************

	public DoublyLinkedList(Node<T> head, Node<T> tail) {
		this.head = head;
		this.tail = tail;
	}

	// *************************************
	// * Getters
	// *************************************

	public Node<T> getHead() {
		return head;
	}

	public Node<T> getTail() {
		return tail;
	}
}
