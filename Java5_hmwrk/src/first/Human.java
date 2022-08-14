package first;

public class Human implements Comparable<Human> {
    private final String name;
    private final String surname;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Human(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "\n" + name + " " + surname;
    }

    @Override
    public int compareTo(Human o) {
        if (o.surname.equals(surname)) {
            return name.compareTo(o.name);
        }
        return surname.compareTo(o.surname);
    }
}
