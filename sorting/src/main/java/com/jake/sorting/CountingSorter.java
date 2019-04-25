package com.jake.sorting;

import java.util.List;

/**
 * For simplicity, this implementation only works on integers so that we don't need a mapping function to derive
 * indices.
 */
public class CountingSorter implements Sorter<Integer> {

    // *************************************
    // * Implementations
    // *************************************

    @Override
    public void sort(List<Integer> list) {
        if (list.isEmpty()) {
            return;
        }

        // Create an array to track the counts of each element
        MinAndRange minAndRange = computeMinAndRange(list);
        int[] counts = new int[minAndRange.range + 1];

        /*
         * Count each element.
         * 
         * To accommodate negative values and to reduce wasted space when all values are positive, shift each index by
         * the min value. This way, the first element of the array will always correspond to the smallest value in the
         * input list.
         */
        list.forEach(element -> counts[element - minAndRange.min]++);

        // Reconstruct the sorted list
        int resultIndex = 0;
        for (int i = 0; i < counts.length; ++i) {
            while (counts[i]-- > 0) {
                list.set(resultIndex++, i + minAndRange.min);
            }
        }
    }

    // *************************************
    // * Helpers
    // *************************************

    private MinAndRange computeMinAndRange(List<Integer> list) {
        int min = list.get(0);
        int max = list.get(0);

        for (int i = 1; i < list.size(); ++i) {
            int element = list.get(i);
            min = Math.min(min, element);
            max = Math.max(max, element);
        }

        return new MinAndRange(min, max - min);
    }

    // *************************************
    // * Helper Class
    // *************************************

    /**
     * Helper class to allow us to return both the min value and range of the input, from a single method call.
     */
    private class MinAndRange {
        private int min;
        private int range;

        private MinAndRange(int min, int range) {
            this.min = min;
            this.range = range;
        }
    }
}
