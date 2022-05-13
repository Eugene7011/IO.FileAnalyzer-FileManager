package com.podzirei.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzer {

    private static final Pattern SENTENCE_PATTERN = Pattern.compile("([.!?])");

    public FileAnalyzer() {
    }

    public FileAnalyzer(File file, String searchedText) {

    }

    public FileStatistics analyze(String path, String word) {
        String content = readContent(path);
        List<String> sentences = splitIntoSentences(content);
        List<String> filteredSentences = filterSentences(sentences, word);

        int count = countWord(filteredSentences, word);
        return new FileStatistics(filteredSentences, count);
    }

    int countWord(List<String> filteredSentences, String word) {
        Pattern pattern = Pattern.compile("\\b" + word + "\\b");

        int count = 0;
        for (String sentence : filteredSentences) {
            Matcher matcher = pattern.matcher(sentence);
            while (matcher.find()) {
                count++;
            }
        }
        return count;
    }

    List<String> filterSentences(List<String> sentences, String word) {
        List<String> filteredSentences = new ArrayList<>();

        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                filteredSentences.add(sentence);
            }
        }
        return filteredSentences;
    }

    List<String> splitIntoSentences(String content) {
        String[] sentences = SENTENCE_PATTERN.split(content);
        List<String> trimmedSentences = new ArrayList<>();

        for (String sentence : sentences) {
            trimmedSentences.add(sentence.trim());
        }

        return trimmedSentences;
    }

    String readContent(String path) {
        File file = new File(path);
        int fileLength = (int) file.length();
        byte[] contentArray = new byte[fileLength];

        try (InputStream inputStream = new FileInputStream(file)) {
            inputStream.read(contentArray);
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        return new String(contentArray);
    }
}
