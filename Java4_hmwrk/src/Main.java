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
        LocalDate newYear = LocalDate.of(2022, 12, 31);
        LocalDate christmas = LocalDate.of(2022, 1, 7);
        LocalDate victoryDay = LocalDate.of(2022, 1, 7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, EEEE", Locale.ENGLISH);
        System.out.println(newYear.format(formatter));
        System.out.println(christmas.format(formatter));
        System.out.println(victoryDay.format(formatter));

        //Третье задание
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Convertation.convertLocalDateTimeToTimestamp(localDateTime);
        System.out.println(timestamp.getNanos());
        System.out.println(timestamp.getTime());

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

        elf1.attack();
        human1.attack();
        orc1.attack();

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

        orc2.getInfoCharacter();
        human2.getInfoCharacter();
        elf2.getInfoCharacter();
    }
}