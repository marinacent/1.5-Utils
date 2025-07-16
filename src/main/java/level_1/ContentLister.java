package level_1;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ContentLister {

    public static File[] listDirContent(String dirPath) throws IOException {
        File dir = new File(dirPath);
        File[] files;
        if (dir.exists() && dir.isDirectory()) {
            files = dir.listFiles();
            if (files != null) {
                Arrays.sort(files, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
            } else {
                throw new IOException("Couldn't access directory contents");
            }
        } else {
            throw new FileNotFoundException("File doesn't exist or isn't a directory");
        }
        return files;
    }

    public static void listRecursively(File dir, int level) throws IOException {
        File[] elements = listDirContent(dir.getPath());
        for (File element : elements) {
            System.out.println(printDirElement(element, level));
            if (element.isDirectory()) {
                listRecursively(element, level + 1);
            }
        }

    }

    public static void listRecursively(File dir, BufferedWriter writer, int level) throws IOException {
        File[] elements = listDirContent(dir.getPath());
        for (File element : elements) {
            writer.write(printDirElement(element, level));
            writer.newLine();
            if (element.isDirectory()) {
                listRecursively(element, writer, level + 1);
            }
        }

    }

    public static void listDirTree(String dirPath) throws IOException {
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            listRecursively(dir, 0);
        } else {
            throw new NotDirectoryException("File is not a directory");
        }
    }

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

    public static String printDirElement(File element, int level) {
        String type = element.isDirectory() ? " (D)" : " (F)";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifiedDate = dateFormat.format(new Date(element.lastModified()));
        String indent = "  ".repeat(level);

        return indent + element.getName() + type + "-- Last modified: " + modifiedDate;
    }

}
