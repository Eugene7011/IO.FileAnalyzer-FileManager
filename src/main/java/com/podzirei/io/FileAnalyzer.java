package com.podzirei.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return (int) Stream.of(filteredSentences.toString().split(" "))
                .filter(elem -> elem.contains(word))
                .count();
    }

    List<String> filterSentences(List<String> sentences, String word) {
        return sentences.stream()
                .filter(s -> s.contains(word))
                .collect(Collectors.toList());
    }

    List<String> splitIntoSentences(String content) {
        List<String> strings = Arrays.asList(SENTENCE_PATTERN.split(content));

        return new ArrayList<>(strings);
    }

    String readContent(String path) {
        File file = new File(path);
        int fileLength = (int) file.length();
        byte[] contentArray = new byte[fileLength];

        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            bufferedInputStream.read(contentArray);
        }catch (IOException e) {
            throw new RuntimeException("IO Exception", e);
        }

        return new String(contentArray);
    }
}
