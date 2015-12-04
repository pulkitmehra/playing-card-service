/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 */
public class RandomizedShuffleTest {

    private List<String> itemsOfSmallSize = Arrays.asList("a", "b", "c");
    private List<String> itemsOfMediumSize = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p");
    private List<String> itemsWithDuplicate = Arrays.asList("a", "b", "a", "d", "a", "f", "g", "h", "i", "j",
        "k", "l", "a", "n", "o", "a");

    private int percentage = 80;
    private Shuffle<String> randomizedShuffle = new RandomizedShuffle<String>();

    @Test
    public void randomizeShuffleOnMediumSizeItemsTest() {
        shuffleTestTemplate(itemsOfMediumSize, percentage);
    }

    @Test
    public void randomizeShuffleOnSmallSizeItemsTest() {
        shuffleTestTemplate(itemsOfSmallSize, 50);
    }

    @Test
    public void randomizeShuffleWithDuplicateItemsTest() {
        shuffleTestTemplate(itemsWithDuplicate, percentage);
    }

    @Test
    public void shuffleOnEmptyOrNullListTest() {
        randomizedShuffle.shuffle(null);
        randomizedShuffle.shuffle(new ArrayList<String>());
    }

    private void shuffleTestTemplate(List<String> items, int percentage) {
        List<String> copyList = new ArrayList<String>(items);
        int size = items.size();

        randomizedShuffle.shuffle(copyList);
        assertThat(copyList, hasSize(size));

        int randomness = shuffleRandomness(items, copyList);
        assertThat(randomness, greaterThanOrEqualTo(acceptableInversion(size, percentage)));
    }

    private int acceptableInversion(int size, int percentage) {
        if (size <= 2) {
            return size;
        }
        return (int) ((size * percentage) / 100);
    }

    private int shuffleRandomness(List<String> originalList, List<String> shuffledList) {
        int inversions = 1;
        for (int i = 0; i < originalList.size(); i++) {
            if (!originalList.get(i).equals(shuffledList.get(i))) {
                inversions++;
            }
        }
        return inversions;
    }

}
