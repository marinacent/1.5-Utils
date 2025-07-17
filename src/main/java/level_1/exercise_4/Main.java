package level_1.exercise_4;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        String TXT_PATH = "src" + File.separator + "data" + File.separator + "poem.txt";

        try {
            TxtProcessor.printFromTxt(TXT_PATH);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
