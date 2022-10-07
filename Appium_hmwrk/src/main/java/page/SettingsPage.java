package page;

import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private WebElement notificationSettings;

    /**
     * Элемент оценки приложения в Play Market
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_rate_settings")
    private WebElement rateInPlayMarket;

    /**
     * Метод клика по настройкам уведомлений
     */
    public NotificationSettingsPage clickNotificationSettings(){
        notificationSettings.click();
        return new NotificationSettingsPage(driver);
    }

    /**
     * Метод клика на поле оценки приложения в Play Market
     */
    public PlayMarketPage clickRateInPlayMarket(){
        rateInPlayMarket.click();
        return new PlayMarketPage(driver);
    }
}
