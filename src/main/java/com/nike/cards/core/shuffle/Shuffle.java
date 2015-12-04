/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.shuffle;

import java.util.List;

/**
 * Shuffle list of items.
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 * @param <T> the generic type
 */
public interface Shuffle<T> {

    /**
     * Shuffle.
     *
     * @param items the items
     */
    public void shuffle(List<T> items);
}
