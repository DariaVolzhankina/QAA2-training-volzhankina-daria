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
        Convertation.convertLocalDateTimeToTimestamp(localDateTime);

        //Четвертое задание
        Attack orc = new Attack() {
            @Override
            public void attack() {
                System.out.println("Орк атакует");
            }
        };
        Attack human = new Attack() {
            @Override
            public void attack() {
                System.out.println("Человек атакует");
            }
        };
        Attack elf = new Attack() {
            @Override
            public void attack() {
                System.out.println("Эльф атакует");
            }
        };
        
        new Thread(
                () -> elf.attack()
        ).start();

        new Thread(
                () -> human.attack()
        ).start();

        new Thread(
                () -> orc.attack()
        ).start();
    }
}
