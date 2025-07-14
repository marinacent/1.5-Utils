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

    public static Object deserialize(String inPath) throws IOException, ClassNotFoundException {
        Object object = null;
        try (
                FileInputStream fileIn = new FileInputStream(inPath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
        ) {
            object = in.readObject();
        }
        return object;
    }

}
