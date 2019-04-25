package com.jake.sorting;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MergeSorter<T extends Comparable<T>> implements Sorter<T> {

    // *************************************
    // * Implementations
    // *************************************

    @Override
    public void sort(List<T> list) {
        doMergeSort(list, 0, list.size() / 2, list.size());
    }

    // *************************************
    // * Helpers
    // *************************************

    private void doMergeSort(List<T> list, int leftStart, int rightStart, int rightEndExclusive) {
        if (leftStart == rightStart) {
            return;
        }

        // Split and sort each side
        doMergeSort(list, leftStart, leftStart + (rightStart - leftStart) / 2, rightStart);
        doMergeSort(list, rightStart, rightStart + (rightEndExclusive - rightStart) / 2, rightEndExclusive);

        // Copy each side into queues
        Queue<T> leftQueue = new LinkedList<>(list.subList(leftStart, rightStart));
        Queue<T> rightQueue = new LinkedList<>(list.subList(rightStart, rightEndExclusive));

        // Merge the results
        int i;
        for (i = leftStart; leftQueue.isEmpty() == false && rightQueue.isEmpty() == false; ++i) {
            //@formatter:off
            Queue<T> toPop = leftQueue.peek().compareTo(rightQueue.peek()) <= 0 ? leftQueue : rightQueue;
            //@formatter:on

            list.set(i, toPop.remove());
        }

        // Drain the leftQueue (if necessary)
        while (leftQueue.isEmpty() == false) {
            list.set(i++, leftQueue.remove());
        }

        // Don't need to drain the right queue; any remaining elements are already in the correct spot
    }
}
