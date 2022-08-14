package first;

import java.util.Comparator;
import java.util.List;

public class City {
    private List<Human> people;

    public City(List<Human> people) {
        this.people = people;
    }

    public List<Human> getPeople() {
        return people;
    }

    public void addHuman2City(List<Human> people, String name, String surname) {
        people.add(new Human(name, surname));
    }

    public void sortPeople() {
        people.sort(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(people);
    }
}
