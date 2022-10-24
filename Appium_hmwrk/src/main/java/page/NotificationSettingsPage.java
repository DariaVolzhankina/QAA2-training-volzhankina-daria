package page;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static io.appium.java_client.touch.offset.PointOption.point;
import static utils.Waiters.waitUntilElementToBeClickable;

/**
 * Класс в котором происходит взаимодействие со страницей настроек уведомлений
 */
@Data
public class NotificationSettingsPage extends Page {
    public NotificationSettingsPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Переключатель уведомлений
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/switch_promotion")
    private WebElement promotionSwitch;

    /**
     * Элемент "Промоакции и скидки"
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_promotions_sales_rbm")
    private WebElement switchPromotionNotifications;

    /**
     * Элемент списка настроек "Частота напоминаний"
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/tv_price_reduction_reminder")
    private WebElement reminderFrequencyButton;

    /**
     * Элемент списка всплывающего окна "Каждые 3 дня"
     */
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]")
    private WebElement every3DaysButton;

    /**
     * Элемент, соответсвующий выбранной частоте напоминаний
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/tv_price_reduction_freq")
    private WebElement reminderFrequencyText;
    ;

    /**
     * Метод клика на переключатель
     *
     * @return Страницу настроек нотификаций
     */
    @Step("Клик на переключатель")
    public NotificationSettingsPage clickSwitchPromotion() {
        waitUntilElementToBeClickable(driver, promotionSwitch);
        promotionSwitch.click();
        return this;
    }

    /**
     * Метод клика на частоту напоминаний
     *
     * @return Страницу настроек нотификаций
     */
    @Step("Клик на поле \"Частота напоминаний\"")
    public NotificationSettingsPage reminderFrequencyClick() {
        reminderFrequencyButton.click();
        return this;
    }

    /**
     * Метод клика на пункт "Каждые 3 дня"
     *
     * @return Страницу настроек нотификаций
     */
    @Step("Клик на поле \"Каждые 3 дня\"")
    public NotificationSettingsPage every3DaysClick() {
        waitUntilElementToBeClickable(driver, every3DaysButton);
        every3DaysButton.click();
        return this;
    }

    @Step("Клик на поле \"Частота напоминаний\"")
    public NotificationSettingsPage checkReminderFrequencyTextIsEvery3Days() {
        Assert.assertEquals(reminderFrequencyText.getText(), "Every 3 days");
        return this;
    }

    @Step
    public NotificationSettingsPage checkPromotionSwitchIsOff() {
        Assert.assertEquals(promotionSwitch.getText(), "OFF");
        return this;
    }

    @Step
    public NotificationSettingsPage checkPromotionSwitchIsOn() {
        waitUntilElementToBeClickable(driver, switchPromotionNotifications);
        Assert.assertEquals(promotionSwitch.getText(), "ON");
        Assert.assertTrue(switchPromotionNotifications.isDisplayed());
        return this;
    }
}
