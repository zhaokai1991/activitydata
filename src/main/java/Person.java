import org.springframework.data.annotation.Id;

/**
 * Created by zhaokai on 16-11-5.
 */
public class Person {

    @Id
    private String id;
    private String name;
    private int age;

    private City city;

    public Person(String name, int age, City city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
