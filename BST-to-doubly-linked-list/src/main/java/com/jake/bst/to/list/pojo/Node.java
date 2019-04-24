package com.jake.bst.to.list.pojo;

/**
 * Simple POJO representing a Node in either a binary tree or a doubly linked list. For brevity, members are made
 * public, and equals()/hashcode()/toString() are omitted.
 */
public class Node<T> {

	// *************************************
	// * Members
	// *************************************

	public T value;
	// Represents the left subtree in a BST, or the previous node in a list
	public Node<T> node1;
	// Represents the right subtree in a BST, or the next node in a list
	public Node<T> node2;

	// *************************************
	// * Constructors
	// *************************************

	public Node(T value) {
		this(value, null, null);
	}

	public Node(T value, Node<T> node1, Node<T> node2) {
		this.value = value;
		this.node1 = node1;
		this.node2 = node2;
	}
}
