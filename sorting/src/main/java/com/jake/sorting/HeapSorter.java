package com.jake.sorting;

import java.util.List;

public class HeapSorter<T extends Comparable<T>> implements Sorter<T> {

    // *************************************
    // * Implementations
    // *************************************

    @Override
    public void sort(List<T> list) {
        // Convert the input into a heap
        buildHeap(list, 0);

        /*
         * Pop the root of the heap and place it into the sorted section at the end of the list (by swapping it with the
         * last element in the heap and shrinking the heap size).
         * 
         * Then, heapify to maintain the max-heap property of the front of the list.
         */
        int heapSize = list.size();
        while (heapSize != 0) {
            swap(list, 0, --heapSize);
            heapify(list, 0, heapSize);
        }
    }

    // *************************************
    // * Utility Helpers
    // *************************************

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return leftChildIndex(index) + 1;
    }

    /**
     * Returns the index of this node's parent, or -1 if this node has no parent (because it is the root node).
     */
    private int parentIndex(int index) {
        return index != 0 ? (index - 1) / 2 : -1;
    }

    private int compareElements(List<T> list, int index1, int index2) {
        //@formatter:off
        return list.get(index1).compareTo(list.get(index2));
        //@formatter:on
    }

    private void swap(List<T> list, int index1, int index2) {
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    // *************************************
    // * Heap Helpers
    // *************************************

    /**
     * Builds a heap out of the entire list by repeatedly adding an element to the end of the heap (by expanding the
     * bound) and up-sifting that new element as needed.
     */
    private void buildHeap(List<T> list, int indexToAddToHeap) {
        if (indexToAddToHeap == list.size()) {
            // There's nothing left to add to the heap, so return.
            return;
        }

        // This could be expressed as a for loop; but it is split for readability
        int indexToSiftUp = indexToAddToHeap;
        int parentIndex = parentIndex(indexToSiftUp);
        while (parentIndex >= 0 && compareElements(list, indexToSiftUp, parentIndex) > 0) {
            swap(list, indexToSiftUp, parentIndex);

            indexToSiftUp = parentIndex;
            parentIndex = parentIndex(indexToSiftUp);
        }

        buildHeap(list, indexToAddToHeap + 1);
    }

    /**
     * Down-sifts the root element until the max-heap property is restored. This method assumes that the max-heap
     * property already holds true for all elements other than the root.
     */
    private void heapify(List<T> list, int rootIndex, int endIndexExclusive) {
        if (rootIndex == endIndexExclusive) {
            // This means we are heapifying an empty heap, so nothing more to do
            return;
        }

        int leftChildIndex = leftChildIndex(rootIndex);

        if (leftChildIndex >= endIndexExclusive) {
            /*
             * This means the root has no children, so nothing more to do.
             * 
             * We don't need to check the right child index since the heap is a complete binary tree. (No node can have
             * a right child without also having a left child.)
             */
            return;
        }

        int rightChildIndex = rightChildIndex(rootIndex);

        /*
         * Determine the index of the greater child.
         * 
         * There may be no right child, in which case the maxChildIndex is the left child's index.
         */
        int maxChildIndex = rightChildIndex < endIndexExclusive
                ? compareElements(list, leftChildIndex, rightChildIndex) > 0 ? leftChildIndex : rightChildIndex
                : leftChildIndex;

        if (compareElements(list, rootIndex, maxChildIndex) < 0) {
            // If the root is less than the greater child, swap them and heapify the child
            swap(list, rootIndex, maxChildIndex);
            heapify(list, maxChildIndex, endIndexExclusive);
        }
    }
}
