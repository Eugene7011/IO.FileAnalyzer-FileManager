package com.podzirei.io;

import java.util.List;

public class FileStatistics {
    List<String> sentences;
    int wordCount;

    public FileStatistics(List<String> sentences, int wordCount) {
        this.sentences = sentences;
        this.wordCount = wordCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public List<String> getSentences() {
        return sentences;
    }

}
