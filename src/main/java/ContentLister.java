import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ContentLister {
    private String dirPath;

    public static File[] listDirContent(String dirPath) {
        // TO DO: THROW APPROPRIATE EXCEPTIONS! ** check if statements
        File dir = new File(dirPath);
        File[] files = null;
        if (dir.exists() && dir.isDirectory()) {
            files = dir.listFiles();
            if (files != null) {
                Arrays.sort(files, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
                for (File file : files) {
                    System.out.println(file.getName());
                }
            }
        }
        return files;
    }

    public static void listRecursively(File dir, BufferedWriter writer) throws IOException {
        // handle exceptions
        // print --> D/F + last modified + indents¿?¿??¿?¿¿??¿?¿
        File[] elements = listDirContent(dir.getPath());
        for (File element : elements) {
            // System.out.println(element);
            writer.write(element.getName());
            writer.newLine();
            if (element.isDirectory()) {
                listRecursively(element, writer);
            }
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
