package level_2;

import level_1.exercise_3.ContentListerToTxt;

import java.util.Optional;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String configPath = "config.properties";
        Optional<ConfigLoader> configOptional = ConfigLoader.load(configPath);

        configOptional.ifPresentOrElse(
                config -> {
                    String dirPath = config.getPath("dirPath");
                    String outPath = config.getPath("outPath");

                    try {
                        ContentListerToTxt.listDirTree(dirPath, outPath);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                },
                () -> {
                    System.out.println("Couldn't load config.properties. Exiting.");
                    System.exit(1);
                }
        );
    }
}
