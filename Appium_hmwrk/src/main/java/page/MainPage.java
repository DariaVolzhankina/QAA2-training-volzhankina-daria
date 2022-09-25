package page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage  {

    private final AndroidDriver driver;

    @FindBy(id = "com.alibaba.aliexpresshd:id/left_action")
    private WebElement hamburger;

    public MainPage(AndroidDriver androidDriver){
        driver = androidDriver;
        PageFactory.initElements(driver, this);
    }

    public MenuPage clickHamburger(){
        hamburger.click();
        return new MenuPage(driver);
    }
}
