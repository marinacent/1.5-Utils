package level_1.exercise_3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

import level_1.exercise_1.DirectoryContentLister;
import level_1.exercise_2.RecursiveContentLister;

public class ContentListerToTxt {

    public static void listDirTree(String dirPath, String outPath) throws IOException {
        File dir = new File(dirPath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outPath))) {
            if (dir.exists() && dir.isDirectory()) {
                listRecursively(dir, writer, 0);
            } else {
                throw new NotDirectoryException("File is not a directory");
            }
        }
    }

    public static void listRecursively(File dir, BufferedWriter writer, int level) throws IOException {
        File[] elements = DirectoryContentLister.listDirContent(dir.getPath());
        for (File element : elements) {
            writer.write(RecursiveContentLister.printDirElement(element, level));
            writer.newLine();
            if (element.isDirectory()) {
                listRecursively(element, writer, level + 1);
            }
        }
    }
}
