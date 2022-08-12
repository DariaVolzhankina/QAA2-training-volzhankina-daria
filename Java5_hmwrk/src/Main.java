import first.City;
import first.Human;
import second.Duplicates;
import third.Anagrams;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Первое
        City city = new City(new ArrayList<>());
        List<Human> people = city.getPeople();

        city.addPeopleToCity(people, "Ivan", "Ivanov");
        city.addPeopleToCity(people, "Boris", "Ivanov");
        city.addPeopleToCity(people, "Anna", "Makarova");
        city.addPeopleToCity(people, "Ivan", "Petrov");
        city.addPeopleToCity(people, "Petr", "Petrov");
        city.addPeopleToCity(people, "Anton", "Matveev");

        city.sortStringByComparator(city.getPeople());

        //Второе
        Duplicates duplicates = new Duplicates();
        duplicates.duplicates("one two three one four three one two five six one");

        //Третье
        Anagrams anagrams = new Anagrams();
        boolean isStringsAnagrams = anagrams.isStringsAnagrams("abc", " bac");
        System.out.println(isStringsAnagrams);
    }
}
