import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String dirPath = "src";
        String outPath = "src/output.txt";
        ContentLister.listDirContent(dirPath);
        try {
            ContentLister.listDirTree(dirPath, outPath);
        } catch (IOException e) {
            System.out.println("Something went wrong unfortunately *.*");
        }

    }
}
