package utils;
import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class TestDataHelper {
    /**
     * Экземпляр класса Faker
     */
    private static final Faker faker = new Faker();

    /**
     * Регулярное выражениек для генерации невалидного пароля
     */
    private static final String INVALID_PASSWORD = "[a-z]{20}";

    /**
     * Невалидный email
     */
    public static final String INVALID_EMAIL = "qweqw@gmail.com";//Здесь без faker,потому что на различные e-mail приложуха выдает разные сообщения

    /**
     * Метод для генерации невалидного пароля
     */
    public static String getRandomInvalidPassword() {
        return faker.regexify(INVALID_PASSWORD);
    }

}
