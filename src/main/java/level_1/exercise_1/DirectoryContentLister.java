package level_1.exercise_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class DirectoryContentLister {

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
}
