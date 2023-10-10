package nl.inholland;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordCounter {
    private Map<String, Integer> wordCounts;

    public WordCounter() {
        wordCounts = new HashMap<>();
        readData();
    }

    public void countWordsInFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line into words
                String[] words = line.split("\\s+");
                for (String word : words) {
                    // Add word to wordCounts
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            // Display error message
            System.err.println("Error reading file: " + file.getName());
        }
    }

    public Map<String, Integer> getWordCounts() {
        return wordCounts;
    }

    private void readData() {
        // Init folder path
        String folderPath = "data/";

        // Get folders from folderPath
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            // Get files from folder
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        countWordsInFile(file);
                    }
                }
            }

            // Display word counts
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            // Display error message
            System.err.println("Invalid folder path.");
        }
    }

    public void shuffleWordCounts() {
        // Get all the entries in the map into a list
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordCounts.entrySet());

        // Shuffle the list
        Collections.shuffle(entries);

        // Insert them all into a LinkedHashMap
        Map<String, Integer> shuffledWordCounts = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            shuffledWordCounts.put(entry.getKey(), entry.getValue());
        }

        // Set wordCounts to the shuffled word counts
        wordCounts = shuffledWordCounts;
    }
}