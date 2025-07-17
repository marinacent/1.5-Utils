package level_1.exercise_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.text.SimpleDateFormat;
import java.util.Date;

import level_1.exercise_1.DirectoryContentLister;

public class RecursiveContentLister {

    public static void listDirTree(String dirPath) throws IOException {
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            listRecursively(dir, 0);
        } else {
            throw new NotDirectoryException("File is not a directory");
        }
    }

    public static void listRecursively(File dir, int level) throws IOException {
        File[] elements = DirectoryContentLister.listDirContent(dir.getPath());
        for (File element : elements) {
            System.out.println(printDirElement(element, level));
            if (element.isDirectory()) {
                listRecursively(element, level + 1);
            }
        }

    }

    public static String printDirElement(File element, int level) {
        String type = element.isDirectory() ? " (D)" : " (F)";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifiedDate = dateFormat.format(new Date(element.lastModified()));
        String indent = "  ".repeat(level);

        return indent + element.getName() + type + " -- Last modified: " + modifiedDate;
    }
}
