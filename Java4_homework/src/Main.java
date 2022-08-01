import interfaces.Attack;
import interfaces.Info;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        //Первое задание
        LocalDate birthday = LocalDate.of(2001, 3, 14);
        LocalDate currentDay = LocalDate.now();
        currentDay.isAfter(birthday);
        currentDay.isBefore(birthday);

        //Второе задание
        LocalDate holiday = LocalDate.of(2022, 12, 31);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, EEEE", Locale.ENGLISH);
        System.out.println(holiday.format(formatter));

        //Третье задание
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Convertation.convertLocalDateTimeToTimestamp(localDateTime);
        System.out.println(timestamp.getNanos() / 1000);

        //Четвертое задание
        Attack orc1 = new Attack() {
            @Override
            public void attack() {
                System.out.println("orc attacks");
            }
        };
        Attack human1 = new Attack() {
            @Override
            public void attack() {
                System.out.println("human attacks");
            }
        };
        Attack elf1 = new Attack() {
            @Override
            public void attack() {
                System.out.println("elf attacks");
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
                System.out.println("It's an orc");
            }
        };
        Info human2 = new Info() {
            @Override
            public void getInfoCharacter() {
                System.out.println("It's an human");
            }
        };
        Info elf2 = new Info() {
            @Override
            public void getInfoCharacter() {
                System.out.println("It's an elf");
            }
        };

        Runnable orc = () -> orc2.getInfoCharacter();
        orc.run();

        Runnable human = () -> human2.getInfoCharacter();
        human.run();

        Runnable elf = () -> elf2.getInfoCharacter();
        elf.run();
    }
}
