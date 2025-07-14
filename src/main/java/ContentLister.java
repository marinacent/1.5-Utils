import java.io.*;
import java.util.Arrays;

public class ContentLister {
    private String dirPath;

    public static File[] listDirContent(String dirPath) throws IOException {
        File dir = new File(dirPath);
        File[] files = null;
        if (dir.exists() && dir.isDirectory()) {
            files = dir.listFiles();
            if (files != null) {
                Arrays.sort(files, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
                for (File file : files) {
                    System.out.println(file.getName());
                }
            } else {
                throw new IOException("Couldn't access directory contents");
            }
        } else {
            throw new FileNotFoundException("File doesn't exist or isn't a directory");
        }
        return files;
    }

    public static void listRecursively(File dir) throws IOException {
        // handle exceptions
        // print --> D/F + last modified + indents¿?¿??¿?¿¿??¿?¿
        File[] elements = listDirContent(dir.getPath());
        for (File element : elements) {
            System.out.println(element);
            if (element.isDirectory()) {
                listRecursively(element);
            }
        }

    }

    public static void listRecursively(File dir, BufferedWriter writer) throws IOException {
        // handle exceptions
        // print --> D/F + last modified + indents¿?¿??¿?¿¿??¿?¿
        File[] elements = listDirContent(dir.getPath());
        for (File element : elements) {
            writer.write(element.getName());
            writer.newLine();
            if (element.isDirectory()) {
                listRecursively(element, writer);
            }
        }

    }

    public static void listDirTree(String dirPath) throws IOException {
        // TO DO: handle exception!
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            listRecursively(dir);
        }
    }

    public static void listDirTree(String dirPath, String outPath) throws IOException {
        // TO DO: handle exception!
        File dir = new File(dirPath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outPath))) {
            if (dir.exists() && dir.isDirectory()) {
                listRecursively(dir, writer);
            }
        }
    }

}
