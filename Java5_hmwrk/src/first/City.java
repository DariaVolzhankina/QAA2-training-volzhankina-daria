package first;

import java.util.List;

public class City {
    private List<Human> people;

    public City(List<Human> people) {
        this.people = people;
    }

    public List<Human> getPeople() {
        return people;
    }
}
