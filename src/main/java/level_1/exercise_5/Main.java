package level_1.exercise_5;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String PERSON_PATH = "src" + File.separator + "data" + File.separator + "person.ser";

        Person sophia = new Person("Sophia Garcia", 45);
        Object garcia = new Object();

        try {
            Serializer.serialize(sophia, PERSON_PATH);
            garcia = (Person) Serializer.deserialize(PERSON_PATH);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (garcia != null) {
            System.out.println(garcia.toString());
        }
    }
}
