package level_1.exercise_3;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String DIR_PATH = "src";
        String OUT_PATH = "src" + File.separator + "data" + File.separator + "output.txt";

        try {
            ContentListerToTxt.listDirTree(DIR_PATH, OUT_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
