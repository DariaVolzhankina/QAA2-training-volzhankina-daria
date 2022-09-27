package page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {

    @FindBy(id = "com.alibaba.aliexpresshd:id/left_action")
    private WebElement hamburger;

    public MainPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public MenuPage clickHamburger(){
        hamburger.click();
        return new MenuPage(driver);
    }
}
