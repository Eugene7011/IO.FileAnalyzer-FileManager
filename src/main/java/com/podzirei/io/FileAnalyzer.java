package com.podzirei.io;

import java.io.*;
import java.util.Scanner;

public class FileAnalyzer {
    public static void main(String[] args) throws IOException {
        System.out.print("Please enter path to File: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        System.out.print("Please enter the word: ");
        String word = scanner.nextLine();

        String content = readContent(path);

        System.out.println("There are " + countOccurrences(content, word) + " words " + word + " in File "
                + path);

        System.out.println("There are following sentences in File, which includes word " + word);
        printAllSentencesInFileWithWord(content, word);
    }

    static void printAllSentencesInFileWithWord(String content, String word) {
        String[] contentArray = content.split("\\n");

        for (String s : contentArray) {
            if (countOccurrences(s, word) > 0) {
                System.out.println(s);
            }
        }
    }

    static int countOccurrences(String content, String word) {
        String[] contentArray = content.split(" ");

        int count = 0;
        for (String s : contentArray) {
            if (word.equals(s))
                count++;
        }
        return count;
    }

    static String readContent(String path) throws IOException {
        File pathToFile = new File(path);
        InputStream inputStream = new FileInputStream(pathToFile);
        int fileLength = (int) pathToFile.length();
        byte[] contentArray = new byte[fileLength];
        inputStream.read(contentArray);

        inputStream.close();

        return new String(contentArray);
    }
}
