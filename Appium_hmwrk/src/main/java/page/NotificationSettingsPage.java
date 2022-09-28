package page;

import io.appium.java_client.android.AndroidDriver;
import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class NotificationSettingsPage extends Page{
    public NotificationSettingsPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(id = "com.alibaba.aliexpresshd:id/switch_promotion")
    private WebElement switchPromotion;

    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_promotions_sales_rbm")
    private WebElement switchPromotionNotifications;

    public NotificationSettingsPage clickSwitchPromotion(){
        switchPromotion.click();
        return new NotificationSettingsPage(driver);
    }
}
