package page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static utils.Waiters.waitUntilElementToBeClickable;

/**
 * Класс в котором происходит взаимодействие со главной страницей
 */
public class MainPage extends Page {

    public MainPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Элемент вызова меню
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/left_action")
    private WebElement hamburger;

    /**
     * Метода вызова меню
     *
     * @return MenuPage - страница меню
     */
    public MenuPage clickHamburger() {
        hamburger.click();
        return new MenuPage(driver);
    }

    /**
     * Метод бокового свайпа для вызова меню
     *
     * @return MenuPage страницу с меню
     */
    @SneakyThrows
    public MenuPage swipe() {
        Dimension size = driver.manage().window().getSize();
        int endx = size.width;
        int startx = (int) (size.width * 0.01);
        int starty = size.height / 3;

        TouchAction touchAction = new TouchAction(driver);
        waitUntilElementToBeClickable(driver,hamburger);
        touchAction.press(point(startx, starty)).waitAction(waitOptions(ofMillis(500))).moveTo(point(endx, starty)).release().perform();
        return new MenuPage(driver);
    }
}