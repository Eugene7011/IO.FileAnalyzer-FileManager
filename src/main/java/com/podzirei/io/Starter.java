package com.podzirei.io;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Starter {

    public static void main(String[] args) throws IOException {
        System.out.print("Please enter path to File: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        if (path.length() == 0){
            System.out.println("Error. Missed path to file argument. Write --help to see right usage");
            return;
        }

        if (path.equals("--help")){
            System.out.println("Usage: java FileAnalyzer [path-to-file] [searchedWord-string");
            System.out.println("Example: java FileAnalyzer C:\\test.txt \"Searched string\"");
        }

        File file = new File(path);

        if(!file.exists()){
            System.out.println("Error! File doesn't exist. Write --help to see right usage");
            return;
        }

        if (!file.isFile()){
            System.out.println("Error! Received path is not referred to file. Write --help to see right usage");
            return;
        }

        if (!file.canRead()){
            System.out.println("Error! Forbidden to read this file. Write --help to see right usage");
            return;
        }

        System.out.print("Please enter the searched word: ");
        String searchedWord = scanner.nextLine();

        FileAnalyzer fileAnalyzer = new FileAnalyzer(file, searchedWord);
        FileStatistics fileStatistics = fileAnalyzer.analyze(path, searchedWord);

        System.out.println();
        printSentencesWithWord(fileStatistics.getSentences());

        System.out.println();
        printCount(fileStatistics.getWordCount());
    }

    private static void printCount(int wordCount) {
        System.out.println("There are "  + wordCount + " matches of searched word in the text.");
    }

    private static void printSentencesWithWord(List<String> sentences) {
        System.out.println("There are following sentences with matches of searched words in the text:");
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }
}

