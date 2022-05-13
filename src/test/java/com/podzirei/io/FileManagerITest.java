package com.podzirei.io;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerITest {
    String pathToCreatedDirWithFiles = "Test";
    String pathToCreatedEmptyDirForPaste = "TestTo";
    String pathToEmptyDir = "Empty";

    @BeforeEach
    void init() throws Exception {
        File path = new File("Test");
        path.mkdir();
        File path1 = new File("Test\\file1.txt");
        path1.createNewFile();
        File path2 = new File("Test\\file2.txt");
        path2.createNewFile();
        File path3 = new File("Test\\file3.txt");
        path3.createNewFile();
        File path4 = new File("Test\\TestInside");
        path4.mkdir();
        File path5 = new File("Test\\TestInside\\file4.txt");
        path5.createNewFile();
        File path6 = new File("Test\\TestInside\\file5.txt");
        path6.createNewFile();
        File path7 = new File("Test\\TestInside\\TestInsideInside");
        path7.mkdir();
        File path8 = new File("Test\\TestInside\\TestInsideInside\\file6.txt");
        path8.createNewFile();

        File path9 = new File("TestTo");
        path9.mkdir();

        File path10 = new File("Empty");
        path10.mkdir();
    }

    @AfterEach
    void delete() {
        File path8 = new File("Test\\TestInside\\TestInsideInside\\file6.txt");
        path8.delete();
        File path7 = new File("Test\\TestInside\\TestInsideInside");
        path7.delete();
        File path6 = new File("Test\\TestInside\\file5.txt");
        path6.delete();
        File path5 = new File("Test\\TestInside\\file4.txt");
        path5.delete();
        File path4 = new File("Test\\TestInside");
        path4.delete();


        File path1 = new File("Test\\file1.txt");
        path1.delete();
        File path2 = new File("Test\\file2.txt");
        path2.delete();
        File path3 = new File("Test\\file3.txt");
        path3.delete();
        File path = new File("Test");
        path.delete();

        File path11 = new File("TestTo\\TestInside\\TestInsideInside\\file6.txt");
        path11.delete();
        File path12 = new File("TestTo\\TestInside\\TestInsideInside");
        path12.delete();
        File path13 = new File("TestTo\\TestInside\\file5.txt");
        path13.delete();
        File path14 = new File("TestTo\\TestInside\\file4.txt");
        path14.delete();
        File path15 = new File("TestTo\\TestInside");
        path15.delete();
        File path16 = new File("TestTo\\file1.txt");
        path16.delete();
        File path17 = new File("TestTo\\file2.txt");
        path17.delete();
        File path18 = new File("TestTo\\file3.txt");
        path18.delete();
        File path9 = new File("TestTo");
        path9.delete();

        File path10 = new File("Empty");
        path10.delete();
    }

    @DisplayName("Test for count files")
    @Test
    public void testCountFiles() {
        assertEquals(6, FileManager.countFiles(pathToCreatedDirWithFiles));
    }

    @DisplayName("Test for count files on empty folder")
    @Test
    public void testCountFilesOnNotEmptyFolder() {
        assertEquals(0, FileManager.countFiles(pathToEmptyDir));
    }

    @DisplayName("Test for count dirs")
    @Test
    public void testCountDirs() {
        assertEquals(2, FileManager.countDirs(pathToCreatedDirWithFiles));
    }

    @DisplayName("Test for count files after copy from dir to new dir")
    @Test
    public void testCountFilesAfterCopy() throws IOException {
        FileManager.copyDirectory(pathToCreatedDirWithFiles, pathToCreatedEmptyDirForPaste);
        assertEquals(FileManager.countFiles(pathToCreatedDirWithFiles), FileManager.countFiles(pathToCreatedEmptyDirForPaste));
    }

    @DisplayName("Test for count files after move from dir to new dir")
    @Test
    public void testCountFilesAfterMove() {
        int countFiles = FileManager.countFiles(pathToCreatedDirWithFiles);
        FileManager.moveDirectory(pathToCreatedDirWithFiles, pathToCreatedEmptyDirForPaste);
        assertEquals(countFiles, FileManager.countFiles(pathToCreatedEmptyDirForPaste));
    }
}
