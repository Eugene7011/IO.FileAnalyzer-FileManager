package com.podzirei.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerTest {

    @DisplayName("Test for count word")
    @Test
    public void testCountWord() throws IOException {
        List<String> sentence = List.of("apple", "dog", "car", "apple");
        FileAnalyzer fileAnalyzer = new FileAnalyzer();

        assertEquals(2, fileAnalyzer.countWord(sentence, "apple"));
    }

    @DisplayName("Test filterSentences")
    @Test
    public void testFilterSentences() throws IOException {
        List<String> sentence = List.of("1 apple", "dog", "car", "2 apple");
        FileAnalyzer fileAnalyzer = new FileAnalyzer();

        List<String> expected = List.of("1 apple", "2 apple");

        assertEquals(expected, fileAnalyzer.filterSentences(sentence, "apple"));
    }

    @DisplayName("Test splitIntoSentences")
    @Test
    public void testSplitIntoSentences() throws IOException {
        String sentence = "Hi! Hello. How are you?";
        FileAnalyzer fileAnalyzer = new FileAnalyzer();

        List<String> expected = List.of("Hi", "Hello", "How are you");

        assertEquals(expected, fileAnalyzer.splitIntoSentences(sentence));
    }
}

