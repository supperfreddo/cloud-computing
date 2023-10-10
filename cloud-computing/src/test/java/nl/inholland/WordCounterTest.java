package nl.inholland;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordCounterTest {

    // Test that the word counter returns more than 0 words
    @Test
    public void wordCounterMoreThanZero() {
        // Init word counter
        WordCounter wordCounter = new WordCounter();

        // Get word counts
        int size = wordCounter.getWordCounts().size();

        // Assert that there are more than 0 words
        assertTrue(size > 0);
    }

    // Test that the multi-threaded word counter returns more than 0 words
    @Test
    public void wordCounterMultiThreadedMoreThanZero() {
        // Init word counter
        WordCounterMultiThreaded wordCounterMultiThreaded = new WordCounterMultiThreaded();

        // Get word counts
        int size = wordCounterMultiThreaded.getWordCounts().size();

        // Assert that there are more than 0 words
        assertTrue(size > 0);
    }

    // Test that both implementations return the same number of words
    // Run the test 10 times beacuse of multi-threading
    @RepeatedTest(10)
    public void singleThreadedEqualsMultiThreaded() {
        // Init both implementations
        WordCounter wordCounter = new WordCounter();
        WordCounterMultiThreaded wordCounterMultiThreaded = new WordCounterMultiThreaded();

        // Get word counts from both implementations
        int sizeSingleThreaded = wordCounter.getWordCounts().size();
        int sizeMultiThreaded = wordCounterMultiThreaded.getWordCounts().size();

        // Assert that both implementations return the same number of words
        assertEquals(sizeSingleThreaded, sizeMultiThreaded);
    }

    // Test that word count is shuffled
    @Test
    public void isWordCountsShuffled() {
        // Init word counter
        WordCounter wordCounter = new WordCounter();

        // Get word counts
        Map<String, Integer> wordCounts = wordCounter.getWordCounts();

        // Shuffle word counts
        wordCounter.shuffleWordCounts();

        // Get shuffled word counts
        Map<String, Integer> shuffledWordCounts = wordCounter.getWordCounts();

        assertTrue(checkShuffled(wordCounts, shuffledWordCounts));
    }

    // Test that word count multi threaded is shuffled
    @Test
    public void isWordCountsMultiThreadedShuffled() {
        // Init word counter
        WordCounterMultiThreaded wordCounterMultiThreaded = new WordCounterMultiThreaded();

        // Get word counts
        Map<String, Integer> wordCounts = wordCounterMultiThreaded.getWordCounts();

        // Shuffle word counts
        wordCounterMultiThreaded.shuffleWordCounts();

        // Get shuffled word counts
        Map<String, Integer> shuffledWordCounts = wordCounterMultiThreaded.getWordCounts();

        assertTrue(checkShuffled(wordCounts, shuffledWordCounts));
    }

    private boolean checkShuffled(Map<String, Integer> originalMap, Map<String, Integer> shuffledMap) {
        // Convert map entries to a list for easier comparison by index
        List<Map.Entry<String, Integer>> originalEntries = new ArrayList<>(originalMap.entrySet());
        List<Map.Entry<String, Integer>> shuffledEntries = new ArrayList<>(shuffledMap.entrySet());

        // Verify that at least one entry has changed position (indicating shuffling)
        for (int i = 0; i < originalEntries.size(); i++) {
            if (!originalEntries.get(i).equals(shuffledEntries.get(i)))
                return true;
        }

        return false;
    }
}