package com.jake.sorting;

import java.util.List;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

	// *************************************
	// * Implementations
	// *************************************

	@Override
	public void sort(List<T> list) {
		doSort(list, 0, list.size() - 1);
	}

	// *************************************
	// * Helpers
	// *************************************

	private void doSort(List<T> list, int startIndex, int endIndexInclusive) {
		if (startIndex < endIndexInclusive) {
			int pivotIndex = partition(list, startIndex, endIndexInclusive);
			// Don't include the pivot when recursing; it is in the correct spot
			doSort(list, startIndex, pivotIndex - 1);
			doSort(list, pivotIndex + 1, endIndexInclusive);
		}
	}

	private int partition(List<T> list, int startIndex, int endIndexInclusive) {
		T pivotValue = list.get(endIndexInclusive);

		/*
		 * Maintain i such that everything to the left of i is less than the pivot value and everything to the right is
		 * greater or equal. To do this, we will swap anything less than the pivot with i, then advance i. In this way,
		 * i will always point to the first element greater than or equal to the pivot, while j will point to either the
		 * first element to the right of i that is less than the pivot (and about to be swapped) or an element greater
		 * than the pivot.
		 * 
		 * After the loop, we will swap the pivot value with i.
		 */
		int i = startIndex;
		for (int j = startIndex; j < endIndexInclusive; ++j) {
			if (list.get(j)
					.compareTo(pivotValue) < 0) {
				swap(list, i++, j);
			}
		}
		swap(list, i, endIndexInclusive);
		return i;
	}

	private void swap(List<T> list, int index1, int index2) {
		T temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}
}
