package page;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage extends Page{

    @FindBy(id = "com.alibaba.aliexpresshd:id/chosen_account_view")
    private WebElement account;

    public MenuPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public LoginPage clickAccount(){
        account.click();
        return new LoginPage(driver);
    }
}
