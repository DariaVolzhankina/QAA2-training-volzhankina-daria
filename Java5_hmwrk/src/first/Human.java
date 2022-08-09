package first;

public class Human {
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Human(String name,String surname){
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "\n" + name + " " + surname;
    }
}
