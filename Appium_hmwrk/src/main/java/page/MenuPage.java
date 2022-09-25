package page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {

    private final AndroidDriver driver;

    @FindBy(id = "com.alibaba.aliexpresshd:id/chosen_account_view")
    private WebElement account;

    public MenuPage(AndroidDriver androidDriver){
        driver = androidDriver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickAccount(){
        account.click();
        return new LoginPage(driver);
    }
}
