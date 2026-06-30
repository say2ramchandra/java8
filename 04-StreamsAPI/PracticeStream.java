import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PracticeStream {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        Person p1Person = new Person(32,"Ramesh");
        Person p2Person = new Person(32,"Mahesh");
        Person p3Person = new Person(30,"Ram");
        Person p4Person = new Person(28,"Ashish");
        persons.add(p1Person);
        persons.add(p2Person);
        persons.add(p3Person);
        persons.add(p4Person);
        System.out.println(persons);

    
         Optional<String> s=persons.stream()
        .filter(p->p.getAge()>31)
        .map(p -> p.getName().toUpperCase())
        .sorted()
        .findAny();

        System.out.println(s.get());
    }
}

class Person{
    private int age;
    private String name;

    
    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
