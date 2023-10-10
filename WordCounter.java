import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    private Map<String, Integer> wordCounts;

    public WordCounter() {
        wordCounts = new HashMap<>();
    }

    public void countWordsInFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.getName());
        }
    }

    public Map<String, Integer> getWordCounts() {
        return wordCounts;
    }

    public static void main(String[] args) {
        String folderPath = "data/"; // Replace with the actual folder path

        WordCounter wordCounter = new WordCounter();

        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        wordCounter.countWordsInFile(file);
                    }
                }
            }

            // Display word counts
            Map<String, Integer> wordCounts = wordCounter.getWordCounts();
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.err.println("Invalid folder path.");
        }
    }
}