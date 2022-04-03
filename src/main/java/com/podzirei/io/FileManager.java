package com.podzirei.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class FileManager {

    public static int countFiles(String pathToFile) {
        int count = 0;
        File file = new File(pathToFile);
        File[] files = file.listFiles();
        if (files != null) {
            for (File innerFile : files) {
                if (innerFile.isFile()) {
                    count++;
                }
                if (innerFile.isDirectory()) {
                    count += countFiles(innerFile.toString());
                }
            }
        }
        return count;
    }

    public static int countDirs(String pathToFile) {
        int count = 0;
        File file = new File(pathToFile);
        if (file.listFiles() != null) {
            for (File innerFile : file.listFiles()) {
                if (innerFile.isDirectory()) {
                    count++;
                    count += countDirs(innerFile.toString());
                }
            }
        }
        return count;
    }

    public static void copyDirectory(String from, String to) throws IOException {
        File sourceDir = new File(from);
        File destDir = new File(to);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        for (String file : Objects.requireNonNull(sourceDir.list())) {
            File source = new File(sourceDir, file);
            File destination = new File(destDir, file);

            if (source.isDirectory()) {
                copyDirectory(source.toString(), destination.toString());
            } else {
                copyFile(source, destination);
            }
        }
    }

    private static void copyFile(File sourceFile, File destinationFile) throws IOException {
        try (FileInputStream input = new FileInputStream(sourceFile);
        FileOutputStream output = new FileOutputStream(destinationFile);) {

            int fileLength = (int) sourceFile.length();
            byte[] buffer = new byte[fileLength];

            output.write(buffer);
        }
    }

    public static void moveDirectory(String from, String to) {

        File sourceDir = new File(from);
        File destDir = new File(to);

        if (!destDir.exists()) {
            destDir.mkdir();
        }

        for (String file : Objects.requireNonNull(sourceDir.list())) {
            File source = new File(sourceDir, file);
            File destination = new File(destDir, file);

            if (source.isDirectory()) {
                moveDirectory(source.toString(), destination.toString());
            } else {
                cutFile(source, destination);
            }
        }
    }

    private static void cutFile(File sourceFile, File destinationFile) {
        boolean renameResult = sourceFile.renameTo(destinationFile);

        System.out.println("Use java io to move from " + sourceFile.toPath() + " to " + destinationFile.toPath());

        if (renameResult) {
            System.out.println(" success. ");
        } else {
            System.out.println(" fail. ");
        }
    }
}
