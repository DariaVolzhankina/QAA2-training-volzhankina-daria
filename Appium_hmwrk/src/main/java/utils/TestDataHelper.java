package utils;
import com.github.javafaker.Faker;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.SneakyThrows;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import page.MenuPage;
import page.Page;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static utils.Waiters.waitUntilElementToBeClickable;

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

    /**
     * Метод бокового свайпа для вызова меню
     *
     * @return MenuPage страницу с меню
     */
    @SneakyThrows
    @Step("Свайп справа налево")
    public static MenuPage swipe(AndroidDriver driver,WebElement element1,WebElement element2){
        Dimension size = driver.manage().window().getSize();
        int endx = size.width;
        int startx = (int) (size.width * 0.01);
        int starty = size.height / 3;

        TouchAction touchAction = new TouchAction(driver);
        waitUntilElementToBeClickable(driver,element1);
        touchAction.press(point(startx, starty)).waitAction(waitOptions(ofMillis(500))).moveTo(point(endx, starty)).release().perform();
        waitUntilElementToBeClickable(driver, element2);
        return new MenuPage(driver);
    }


    /**
     * Метод вертикального свайпа
     *
     * @return Страницу,на которой произошел свайп
     */
    @SneakyThrows
    public static <T extends Page> T swipeVertical(AndroidDriver driver,T page) {
        Dimension size = driver.manage().window().getSize();
        int endy = (int) (size.height * 0.1);
        int starty = (int) (size.height * 0.9);
        int startx = size.width / 2;

        TouchAction touchAction = new TouchAction(driver);
        Thread.sleep(5000);
        touchAction.press(point(startx, starty)).waitAction(waitOptions(ofMillis(500))).moveTo(point(startx, endy)).release().perform();
        return page;
    }
}
