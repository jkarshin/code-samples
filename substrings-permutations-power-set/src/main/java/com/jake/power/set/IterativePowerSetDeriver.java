package com.jake.power.set;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link PowerSetDeriver} that represents each subset as a binary string whose length is equal to the
 * size of the input set. Each bit corresponds to an element in the input; each '1' bit indicates that the element that
 * corresponds to this bit should be included in this particular subset.<br>
 * <br>
 * By iterating over all binary strings of the indicated length, we derive all subsets of the input set.
 */
public class IterativePowerSetDeriver implements PowerSetDeriver {

    // *************************************
    // * Implementations
    // *************************************

    @Override
    public <T> Set<Set<T>> derive(Set<T> set) {
        Set<Set<T>> subsets = new HashSet<>();
        BitSet bits = new BitSet(set.size());
        // Add the elements to a list for quick random access (by index)
        List<T> orderedElements = new ArrayList<>(set);

        do {
            subsets.add(deriveSubset(bits, orderedElements));
        } while (incrementBits(bits, set.size()));

        return subsets;
    }

    // *************************************
    // * Helpers
    // *************************************

    /**
     * If the bitset consists entirely of 1's, returns false; otherwise, increments the bitset by 1 and returns
     * true.<br>
     * <br>
     * We must pass in the number of bits, as there is no way to obtain this from the bitset. ({@link BitSet#size()}
     * returns the true number of bits in use by the BitSet; not the logical number of bits being used.)
     */
    private boolean incrementBits(BitSet bits, int numBits) {
        if (bits.cardinality() == numBits) {
            return false;
        } else {
            int bitToSet = bits.nextClearBit(0);
            bits.clear(0, bitToSet);
            bits.set(bitToSet);
            return true;
        }
    }

    private <T> Set<T> deriveSubset(BitSet bits, List<T> orderedElements) {
        Set<T> subset = new HashSet<>();

        for (int i = bits.nextSetBit(0); i >= 0; i = bits.nextSetBit(i + 1)) {
            subset.add(orderedElements.get(i));
        }

        return subset;
    }
}
