import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        //Первое задание
        LocalDate birthday = LocalDate.of(2001,3,14);
        LocalDate currentDay = LocalDate.now();
        currentDay.isAfter(birthday);
        currentDay.isBefore(birthday);

        //Второе задание
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, EEEE", Locale.ENGLISH);
        System.out.println(currentDay.format(formatter));

        //Третье задание
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Convertation.convertLocalDateTimeToTimestamp(localDateTime);
        System.out.println(timestamp.getNanos()/1000);

        //Четвертое задание
        Attack orc1 = new Attack() {
            @Override
            public void attack() {
                System.out.println("Орк атакует");
            }
        };
        Attack human1 = new Attack() {
            @Override
            public void attack() {
                System.out.println("Человек атакует");
            }
        };
        Attack elf1 = new Attack() {
            @Override
            public void attack() {
                System.out.println("Эльф атакует");
            }
        };
        
        new Thread(
                () -> elf1.attack()
        ).start();

        new Thread(
                () -> human1.attack()
        ).start();

        new Thread(
                () -> orc1.attack()
        ).start();

        //Пятое задание
        Info orc2 = new Info() {
            @Override
            public void getInfoCharacter() {
                System.out.println("Это орк");
            }
        };
        Info human2 = new Info() {
            @Override
            public void getInfoCharacter() {
                System.out.println("Это человек");
            }
        };
        Info elf2 = new Info() {
            @Override
            public void getInfoCharacter() {
                System.out.println("Это эльф");
            }
        };

        new Thread(
                () -> elf2.getInfoCharacter()
        ).start();

        new Thread(
                () -> human2.getInfoCharacter()
        ).start();

        new Thread(
                () -> orc2.getInfoCharacter()
        ).start();
    }
}
