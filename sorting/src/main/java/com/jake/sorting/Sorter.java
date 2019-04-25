package com.jake.sorting;

import java.util.List;

/**
 * Interface for a sorting algorithm.
 */
public interface Sorter<T extends Comparable<T>> {
	void sort(List<T> list);
}
