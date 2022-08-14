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

        city.addHuman2City(people, "Ivan", "Ivanov");
        city.addHuman2City(people, "Boris", "Ivanov");
        city.addHuman2City(people, "Anna", "Makarova");
        city.addHuman2City(people, "Ivan", "Petrov");
        city.addHuman2City(people, "Petr", "Petrov");
        city.addHuman2City(people, "Anton", "Matveev");

        city.sortPeople();

        //Второе
        Duplicates duplicates = new Duplicates();
        duplicates.duplicates("one two three one four three one two five six one");

        //Третье
        Anagrams anagrams = new Anagrams();
        boolean isStringsAnagrams = anagrams.isStringsAnagrams("abc", " bac");
        System.out.println(isStringsAnagrams);
    }
}
