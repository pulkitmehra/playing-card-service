/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.shuffle;

import java.util.List;
import java.util.ListIterator;


/**
 * Human style splitting and interleaving shuffle
 * <p>
 *      <ul> deck = 12345678
 *      <ul> split1 = 1234  & split2= 5678
 *      <ul> interleaving = 51627384
 * </p>
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 * @param <T> the generic type
 */
public class SplittingInterleavingShuffle<T> implements Shuffle<T> {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void shuffle(List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }

        int hi = items.size();
        int lo = 0;
        int mid = lo + ((hi - lo) / 2);

        Object[] temp = new Object[items.size()];
        Object[] arr = items.toArray();

        int i = lo;
        int j = mid;
        int k = lo;
        while (i < mid && j < hi) {
            temp[k++] = arr[j++];
            temp[k++] = arr[i++];
        }

        while (j < hi) {
            temp[k++] = arr[j++];
        }

        ListIterator it = items.listIterator();
        for (int p = 0; p < arr.length; p++) {
            it.next();
            it.set(temp[p]);
        }
    }

}
