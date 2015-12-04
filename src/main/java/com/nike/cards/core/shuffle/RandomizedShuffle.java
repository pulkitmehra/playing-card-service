/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.shuffle;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Shuffling in a randomized way. 
 * It takes an array length and pick a random index within that limit and 
 * swap it with other array element.
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 * @param <T> shuffle data type
 */
public class RandomizedShuffle<T> implements Shuffle<T> {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void shuffle(List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }

        int size = items.size();
        Object arr[] = items.toArray();
        for (int i = size; i > 1; i--) {
            swap(arr, i - 1, ThreadLocalRandom.current().nextInt(i));
        }

        ListIterator it = items.listIterator();
        for (int i = 0; i < arr.length; i++) {
            it.next();
            it.set(arr[i]);
        }

    }

    /**
     * Swap.
     *
     * @param arr the arr
     * @param i the i
     * @param j the j
     */
    private static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
