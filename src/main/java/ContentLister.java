import java.io.File;
import java.util.Arrays;

public class ContentLister {
    private String dirPath;

    public static void listDirContent(String dirPath) {
        // TO DO: THROW APPROPIATE EXCEPTIONS! ** check if statements
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
}
