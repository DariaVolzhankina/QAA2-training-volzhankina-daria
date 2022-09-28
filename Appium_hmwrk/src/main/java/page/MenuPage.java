package page;


import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Data
public class MenuPage extends Page{

    public MenuPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(id = "com.alibaba.aliexpresshd:id/chosen_account_view")
    private WebElement account;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.ScrollView/android.widget.LinearLayout")
    private WebElement menu;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[9]")
    private WebElement settings;

    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_rate_settings")
    private WebElement rateOnPlayMarket;

    public LoginPage clickAccount(){
        account.click();
        return new LoginPage(driver);
    }

    public SettingsPage clickSettings(){
        settings.click();
        return new SettingsPage(driver);
    }

    public void clickRateOnPlayMarket(){
        rateOnPlayMarket.click();
    }
}
