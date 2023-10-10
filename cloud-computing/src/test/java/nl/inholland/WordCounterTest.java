package nl.inholland;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterTest {

    // Test that the word counter returns more than 0 words
    @Test
    public void wordCounterMoreThanZero() {
        // Init word counter
        WordCounter wordCounter = new WordCounter();

        // Get word counts
        int size = wordCounter.getWordCounts().size();

        // Assert that there are more than 0 words
        assertEquals(true, size > 0);
    }

    // Test that the multi-threaded word counter returns more than 0 words
    @Test
    public void wordCounterMultiThreadedMoreThanZero() {
        // Init word counter
        WordCounterMultiThreaded wordCounterMultiThreaded = new WordCounterMultiThreaded();

        // Get word counts
        int size = wordCounterMultiThreaded.getWordCounts().size();

        // Assert that there are more than 0 words
        assertEquals(true, size > 0);
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
}