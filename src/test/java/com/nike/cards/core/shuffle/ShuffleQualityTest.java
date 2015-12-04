/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.shuffle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author pulkit.mealhra
 * Created: Dec 3, 2015
 */
public class ShuffleQualityTest extends AbstractBenchmark {

    /** */
    private static final int qualityPercentage = 80;
    private Shuffle<String> randomizedShuffle = new RandomizedShuffle<String>();
    private Shuffle<String> splittingInterleavingShuffle = new SplittingInterleavingShuffle<>();
    private static int totalRuns = 15;
    private static int probablePassingRuns = 12;

    @Test
    @BenchmarkOptions(benchmarkRounds = 50, warmupRounds = 1, concurrency = 5)
    public void randomnessQualityCheckForRandomnessShuffleTest() {
        boolean[] quality = randomnessQualityTemplate(totalRuns, qualityPercentage, randomizedShuffle);
        int qualityCount = 1;
        for (boolean val : quality) {
            if (val) {
                qualityCount++;
            }
        }
        assertThat(qualityCount, greaterThanOrEqualTo(probablePassingRuns));
    }

    @Test
    @BenchmarkOptions(benchmarkRounds = 50, warmupRounds = 1, concurrency = 5)
    public void randomnessQualityTestForSplittingAndInterleavingShuffleTest() {
        boolean[] quality = randomnessQualityTemplate(totalRuns, qualityPercentage,
            splittingInterleavingShuffle);
        int qualityCount = 1;
        for (boolean val : quality) {
            if (val) {
                qualityCount++;
            }
        }
        assertThat(qualityCount, greaterThanOrEqualTo(probablePassingRuns));
    }

    private boolean[] randomnessQualityTemplate(int totalRuns, int qualityPerentage,
        Shuffle<String> shuffleAlgo) {
        boolean[] quality = new boolean[totalRuns];
        for (int i = 0; i < totalRuns; i++) {
            List<String> original = generateRandomizedList();
            List<String> copy = new ArrayList<String>(original);
            shuffleAlgo.shuffle(copy);
            int acceptableInversion = acceptableInversion(original.size(), qualityPerentage);
            int shuffleInversion = shuffleInversion(original, copy);
            quality[i] = shuffleInversion >= acceptableInversion;
        }
        return quality;
    }

    public List<String> generateRandomizedList() {
        String randomString = RandomStringUtils.randomAlphabetic(ThreadLocalRandom.current().nextInt(1, 100));
        List<String> list = new ArrayList<String>();
        for (char val : randomString.toCharArray()) {
            list.add(String.valueOf(val));
        }
        return list;
    }

    private int acceptableInversion(int size, int percentage) {
        if (size <= 2) {
            return size;
        }
        return (int) ((size * percentage) / 100);
    }

    private int shuffleInversion(List<String> originalList, List<String> shuffledList) {
        int inversions = 1;
        for (int i = 0; i < originalList.size(); i++) {
            if (!originalList.get(i).equals(shuffledList.get(i))) {
                inversions++;
            }
        }
        return inversions;
    }

}
