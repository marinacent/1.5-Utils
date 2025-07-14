import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer {

    public static void serialize(Object object, String outPath) throws IOException {
        try (
            FileOutputStream fileOut = new FileOutputStream(outPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
        ) {
            out.writeObject(object);
        }
    }

    }
