package page;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.Waiters.waitUntilElementToBeClickable;
import static utils.Waiters.waitUntilVisibilityOfElement;

/**
 * Класс в котором происходит взаимодействие со страницей настроек
 */
@Data
public class SettingsPage extends Page{
    public SettingsPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Элемент настроек уведомлений
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_notification_settings")
    private WebElement notificationSettingsButton;

    /**
     * Элемент оценки приложения в Play Market
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_rate_settings")
    private WebElement rateInPlayMarketButton;

    /**
     * Метод клика по настройкам уведомлений
     */
    @Step("Клик на настройки уведомлений")
    public NotificationSettingsPage clickNotificationSettings(){
        waitUntilElementToBeClickable(driver, notificationSettingsButton);
        notificationSettingsButton.click();
        return new NotificationSettingsPage(driver);
    }

    /**
     * Метод клика на поле оценки приложения в Play Market
     */
    @Step("Клик на поле оценки приложения в Play Market")
    public PlayMarketPage clickRateInPlayMarket(){
        waitUntilVisibilityOfElement(driver, rateInPlayMarketButton);
        rateInPlayMarketButton.click();
        return new PlayMarketPage(driver);
    }
}
