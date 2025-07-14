import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String age;

    public Person(String age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name;
    }
}
