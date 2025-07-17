package level_1.exercise_1;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String DIR_PATH = "src";

        try {
            for ( File file : DirectoryContentLister.listDirContent(DIR_PATH)) {
                System.out.println(file.getName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
