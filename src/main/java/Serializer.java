import java.io.*;

public class Serializer {

    public static void serialize(Object object, String outPath) throws IOException {
        try (
            FileOutputStream fileOut = new FileOutputStream(outPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
        ) {
            out.writeObject(object);
        }
    }

    public static void deserialize(Object nullObject, String inPath) throws IOException, ClassNotFoundException {
        try (
                FileInputStream fileIn = new FileInputStream(inPath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                ) {
            nullObject = in.readObject();
        }
    }

    }
