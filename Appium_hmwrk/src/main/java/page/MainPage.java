package page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class MainPage extends Page {

    @FindBy(id = "com.alibaba.aliexpresshd:id/left_action")
    private WebElement hamburger;

    public MainPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public MenuPage clickHamburger() {
        hamburger.click();
        return new MenuPage(driver);
    }

    @SneakyThrows
    public MenuPage swipe() throws InterruptedException {
        Dimension size = driver.manage().window().getSize();
        int endx = size.width;
        int startx = (int) (size.width * 0.01);
        int starty = size.height / 3;

        TouchAction touchAction = new TouchAction(driver);
        Thread.sleep(5000);
        touchAction.press(point(startx, starty)).waitAction(waitOptions(ofMillis(500))).moveTo(point(endx, starty)).release().perform();
        return new MenuPage(driver);
    }
}