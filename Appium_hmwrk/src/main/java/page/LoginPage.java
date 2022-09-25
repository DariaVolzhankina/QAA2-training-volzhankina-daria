package page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final AndroidDriver driver;

    @FindBy(id = "com.alibaba.aliexpresshd:id/btn_sign_in")
    private WebElement signInButton;

    public LoginPage(AndroidDriver androidDriver){
        driver = androidDriver;
        PageFactory.initElements(driver, this);
    }

    public SignInPage clickSignInButton(){
        signInButton.click();
        return new SignInPage(driver);
    }
}
