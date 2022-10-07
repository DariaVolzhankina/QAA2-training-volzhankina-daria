package page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import lombok.SneakyThrows;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

/**
 * Класс в котором происходит взаимодействие со страницей настроек уведомлений
 */
@Data
public class NotificationSettingsPage extends Page{
    public NotificationSettingsPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Переключатель уведомлений
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/switch_promotion")
    private WebElement switchPromotion;

    /**
     * Элемент "Промоакции и скидки"
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_promotions_sales_rbm")
    private WebElement switchPromotionNotifications;

    /**
     * Элемент списка настроек "Частота напоминаний"
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/tv_price_reduction_reminder")
    private WebElement reminderFrequency;

    /**
     * Элемент списка всплывающего окна "Каждые 3 дня"
     */
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]")
    private WebElement every3Days;

    /**
     * Элемент, соответсвующий выбранной частоте напоминаний
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/tv_price_reduction_freq")
    private WebElement reminderFrequencyText;;

    /**
     * Метод клика на переключатель
     *
     * @return Страницу настроек нотификаций
     */
    public NotificationSettingsPage clickSwitchPromotion(){
        switchPromotion.click();
        return new NotificationSettingsPage(driver);
    }

    /**
     * Метод вертикального свайпа
     *
     * @return Страницу настроек нотификаций
     */
    @SneakyThrows
    public NotificationSettingsPage swipeVertical() {
        Dimension size = driver.manage().window().getSize();
        int endy = (int) (size.height * 0.1);
        int starty = (int) (size.height * 0.9);
        int startx = size.width / 2;

        TouchAction touchAction = new TouchAction(driver);
        Thread.sleep(5000);
        touchAction.press(point(startx, starty)).waitAction(waitOptions(ofMillis(500))).moveTo(point(startx, endy)).release().perform();
        return new NotificationSettingsPage(driver);
    }

    /**
     * Метод клика на частоту напоминаний
     *
     * @return Страницу настроек нотификаций
     */
    public NotificationSettingsPage reminderFrequencyClick(){
        reminderFrequency.click();
        return new NotificationSettingsPage(driver);
    }

    /**
     * Метод клика на пункт "Каждые 3 дня"
     *
     * @return Страницу настроек нотификаций
     */
    public NotificationSettingsPage every3DaysClick(){
        every3Days.click();
        return new NotificationSettingsPage(driver);
    }
}
