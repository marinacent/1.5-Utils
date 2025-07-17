package level_1.exercise_2;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String DIR_PATH = "src";

        try {
            RecursiveContentLister.listDirTree(DIR_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
