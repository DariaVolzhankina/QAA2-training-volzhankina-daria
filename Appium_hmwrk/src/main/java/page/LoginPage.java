package page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page{

    @FindBy(id = "com.alibaba.aliexpresshd:id/btn_sign_in")
    private WebElement signInButton;

    public LoginPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public SignInPage clickSignInButton(){
        signInButton.click();
        return new SignInPage(driver);
    }
}
