package com.podzirei.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileAnalyzer {

    private File file;
    private String path;
    private String searchedWord;

    public FileAnalyzer(File file, String searchedText) {
        this.file = file;
        this.searchedWord = searchedText;
        this.path = file.getPath();
    }

    public FileStatistics analyze(String path, String word) throws IOException {
        String content = readContent(path);
        List<String> sentences = splitIntoSentences(content);
        List<String> filteredSentences = filter(sentences, word);

        int count = countWord(filteredSentences, word);
        return new FileStatistics(filteredSentences, count);
    }

    private int countWord(List<String> filteredSentences, String word) {
        int count = 0;
        for (String sentence : filteredSentences) {
            String[] wordsInSentence = sentence.split(" ");
            for (String s : wordsInSentence) {
                if (Objects.equals(s, word)) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<String> filter(List<String> sentences, String word) {
        List<String> filteredSentences = new ArrayList<>();

        for (String sentence : sentences) {
            String[] wordsInSentence = sentence.split(" ");
            for (String s : wordsInSentence) {
                if (Objects.equals(s, word)){
                    filteredSentences.add(sentence);
                    break;
                }
            }
        }
        return filteredSentences;
    }

    public List<String> splitIntoSentences(String content) {
        return List.of(content.split("[\\.\\!\\?]"));
    }

    public String readContent(String path) throws IOException {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        int fileLength = (int) file.length();
        byte[] contentArray = new byte[fileLength];

        inputStream.read(contentArray);
        inputStream.close();

        return new String(contentArray);
    }


}
