/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 */
public class SplittingInterleavingShuffleTest {

    private List<String> evenItems = Arrays.asList("a", "b", "c", "d", "e", "f");
    private List<String> oddItemsItems = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");

    private List<String> shuffledEvenItems = Arrays.asList("d", "a", "e", "b", "f", "c");
    private List<String> shuffledOddItems = Arrays.asList("e", "a", "f", "b", "g", "c", "h", "d", "i");

    private Shuffle<String> splittingInterleavingShuffle = new SplittingInterleavingShuffle<String>();

    @Test
    public void evenItemsShuffleTest() {
        ArrayList<String> copyOfItems = new ArrayList<String>(evenItems);
        splittingInterleavingShuffle.shuffle(copyOfItems);
        assertShuffledList(copyOfItems, shuffledEvenItems);
    }

    @Test
    public void oddItemsShuffleTest() {
        ArrayList<String> copyOfItems = new ArrayList<String>(oddItemsItems);
        splittingInterleavingShuffle.shuffle(copyOfItems);
        assertShuffledList(copyOfItems, shuffledOddItems);
    }

    private void assertShuffledList(List<String> shuffled, List<String> result) {
        for (int i = 0; i < shuffled.size(); i++) {
            if (!shuffled.get(i).equals(result.get(i))) {
                Assert.fail(String.format(
                    "\n shuffled list %s \n result list %s \n item that does not match '%s' != '%s'",
                    StringUtils.join(shuffled, ','), StringUtils.join(result, ','), shuffled.get(i),
                    result.get(i)));
            }
        }
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
