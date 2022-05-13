package com.podzirei.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerITest {
    String searchedWord = "jeans";

    public FileAnalyzerITest() {
    }

    @BeforeEach
    void init() throws Exception {
        File path = new File("Test");
        path.mkdir();

        File path1 = new File("Test\\file1.txt");
        path1.createNewFile();
        OutputStream outputStream = new FileOutputStream(path1);
        String text = "After the Second World War, jeans became popular all over the world. " +
                "Today, blue jeans are made throughout the world – most of them in Asia. " +
                "Very few jeans are now made in the USA, because of the cost: but it is still possible to buy blue jeans that are made in San Francisco. " +
                "if you have a lot of money to spend.";
        byte[] contentArray = text.getBytes();
        outputStream.write(contentArray);
        outputStream.close();
    }

    @AfterEach
    void delete() {
        File path1 = new File("Test\\file1.txt");
        path1.delete();

        File path = new File("Test");
        path.delete();
    }

    @DisplayName("Test for count matches of searched word")
    @Test
    public void testCountMatchesOfSearchedWord() throws IOException {
        File path1 = new File("Test\\file1.txt");
        FileAnalyzer fileAnalyzer = new FileAnalyzer(path1, searchedWord);
        FileStatistics fileStatistics = fileAnalyzer.analyze("Test\\file1.txt", searchedWord);

        assertEquals(4, fileStatistics.getWordCount());
    }

    @DisplayName("Test for checking filtered sentences with matches of searched word")
    @Test
    public void testCheckingFilteredSentencesWithMatchesOfSearchedWord() throws IOException {
        File path1 = new File("Test\\file1.txt");
        FileAnalyzer fileAnalyzer = new FileAnalyzer(path1, searchedWord);
        FileStatistics fileStatistics = fileAnalyzer.analyze("Test\\file1.txt", searchedWord);

        String content = fileAnalyzer.readContent("Test\\file1.txt");
        List<String> sentences = fileAnalyzer.splitIntoSentences(content);

        StringBuilder expectedSentences = new StringBuilder();

        for (int i = 0; i < sentences.size() - 1; i++) {
            expectedSentences.append(sentences.get(i));
            if (i != sentences.size() - 2) {
                expectedSentences.append(", ");
            }
        }

        assertEquals(fileStatistics.getSentences().toString(), "[" + expectedSentences + "]");
    }

    @DisplayName("Test to check if readContent works correct")
    @Test
    public void testToCheckIfReadContentWorksCorrect() throws IOException {
        File path1 = new File("Test\\file1.txt");
        String text = "After the Second World War, jeans became popular all over the world. " +
                "Today, blue jeans are made throughout the world – most of them in Asia. " +
                "Very few jeans are now made in the USA, because of the cost: but it is still possible to buy blue jeans that are made in San Francisco. " +
                "if you have a lot of money to spend.";
        FileAnalyzer fileAnalyzer = new FileAnalyzer(path1, searchedWord);

        assertEquals(fileAnalyzer.readContent("Test\\file1.txt"), text);
    }

    @DisplayName("Test filteredSentences() method with matching and not matching elements")
    @Test
    public void testToCheckFilteredSentencesWithMatchingAndNotElements() {
        List<String> inputSentences = List.of("After the Second World War", " jeans", "Many jeans", "nothing");
        List<String> expectedSentences = List.of(" jeans", "Many jeans");
        FileAnalyzer fileAnalyzer = new FileAnalyzer();

        assertEquals(expectedSentences, fileAnalyzer.filterSentences(inputSentences, searchedWord));
    }

    @DisplayName("Test filteredSentences() method with empty input")
    @Test
    public void testToCheckFilteredSentencesWithEmptyInput() {
        List<String> inputSentences = Collections.emptyList();
        FileAnalyzer fileAnalyzer = new FileAnalyzer();

        assertEquals(inputSentences, fileAnalyzer.filterSentences(inputSentences, searchedWord));
    }

    @DisplayName("Test filteredSentences() method when there is no matching elements")
    @Test
    public void testToCheckFilteredSentencesWhenNoneOfSentencesIncludeSearchedWord() {
        List<String> inputSentences = List.of("After the Second World War", " jeans", "Many jeans", "nothing");
        List<String> expectedSentences = Collections.emptyList();
        String word = "coca-cola";
        FileAnalyzer fileAnalyzer = new FileAnalyzer();

        assertEquals(expectedSentences, fileAnalyzer.filterSentences(inputSentences, word));
    }

}
