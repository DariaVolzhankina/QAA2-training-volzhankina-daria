import first.City;
import first.Human;
import second.Duplicates;
import third.Anagrams;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        //Первое
        City city = new City(new ArrayList<>());

        city.getPeople().add(new Human("Ivan", "Ivanov"));
        city.getPeople().add(new Human("Boris", "Ivanov"));
        city.getPeople().add(new Human("Anna", "Makarova"));
        city.getPeople().add(new Human("Ivan", "Petrov"));
        city.getPeople().add(new Human("Petr", "Petrov"));
        city.getPeople().add(new Human("Anton", "Matveev"));

        city.getPeople().sort(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                if(o1.getSurname() == o2.getSurname()){
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });

        System.out.println(city.getPeople());

        //Второе
        Duplicates duplicates = new Duplicates();
        duplicates.duplicates("one two three one four three one two five six one");

        //Третье
        Anagrams anagrams = new Anagrams();
        boolean isStringsAnagrams = anagrams.isStringsAnagrams("abc", "bac");
        System.out.println(isStringsAnagrams);
    }
}