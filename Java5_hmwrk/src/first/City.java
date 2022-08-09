package first;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class City {
    public static void main(String[] args) {
        List<Human> people = new ArrayList<>();
        people.add(new Human("Ivan", "Ivanov"));
        people.add(new Human("Boris", "Ivanov"));
        people.add(new Human("Anna", "Makarova"));
        people.add(new Human("Ivan", "Petrov"));
        people.add(new Human("Petr", "Petrov"));
        people.add(new Human("Anton", "Matveev"));

        people.sort(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                if(o1.getSurname() == o2.getSurname()){
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });
        System.out.println(people);
    }
}
