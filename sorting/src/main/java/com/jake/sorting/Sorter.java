package com.jake.sorting;

import java.util.List;

/**
 * Interface for a sorting algorithm. Input lists are expected to contain only non-null elements.
 */
public interface Sorter<T extends Comparable<T>> {
    void sort(List<T> list);
}
