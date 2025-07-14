import java.io.File;
import java.util.Arrays;

public class ContentLister {
    private String dirPath;

    public static void listDirContent(String dirPath) {
        // TO DO: THROW APPROPRIATE EXCEPTIONS! ** check if statements
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            String[] files = dir.list();
            if (files != null) {
                Arrays.sort(files);
                for (String file: files) {
                    System.out.println(file);
            }
            }
        }
    }

    public static void listRecursively(File dir) {
        // handle exceptions
        // print --> D/F + last modified + indents¿?¿??¿?¿¿??¿?¿
        File[] elements = dir.listFiles();
        if (elements != null) {
            Arrays.sort(elements, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
            for (File element: elements) {
                System.out.println(element);
                if (element.isDirectory()) {
                    listRecursively(element);
                }
            }

        }
    }

    public static void listDirTree(String dirPath) {
        // TO DO: handle exception!
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            listRecursively(dir);
        }
    }

}
