package nl.inholland;

import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterTest {

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