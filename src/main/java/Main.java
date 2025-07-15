import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
//        String dirPath = "src";
//        String outPath = "src" + File.separator + "data" + File.separator + "output.txt";
//        String txtFilePath = "src" + File.separator + "data" + File.separator + "poem.txt";
//        String personPath = "src" + File.separator + "data" + File.separator + "person.ser";

        String configPath = "config.properties";
        Optional<ConfigLoader> configOptional = ConfigLoader.load(configPath);

        if (configOptional.isPresent()) {
            ConfigLoader config = configOptional.get();
            String dirPath = config.getProperty("dirPath");
            String outPath = config.getProperty("outPath");
            String txtFilePath = config.getProperty("txtFilePath");
            String personPath = config.getProperty("personPath");
        } else {
            System.out.println("Couldn't load config.properties file. Exiting.");
            System.exit(1);
        }

        Person sophia = new Person("Sophia Garcia", 45);
        Object garcia = null;


        try {
            for (File file : ContentLister.listDirContent(dirPath)) {
                System.out.println(file.getName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            ContentLister.listDirTree(dirPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            ContentLister.listDirTree(dirPath, outPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            TxtProcessor.printFromTxt(txtFilePath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Serializer.serialize(sophia, personPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            garcia = (Person) Serializer.deserialize(personPath);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        if (garcia != null) {
            System.out.println(garcia.toString());
        }


    }
}
