package page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    private final AndroidDriver driver;

    public SignInPage(AndroidDriver androidDriver){
        driver = androidDriver;
        PageFactory.initElements(driver, this);
    }
}
