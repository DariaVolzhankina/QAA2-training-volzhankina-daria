package page;

import io.appium.java_client.android.AndroidDriver;
import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class SettingsPage extends Page{
    public SettingsPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_notification_settings")
    private WebElement notificationSettings;

    public NotificationSettingsPage clickNotificationSettings(){
        notificationSettings.click();
        return new NotificationSettingsPage(driver);
    }
}
