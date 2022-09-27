package page;

import accountData.AccountData;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends Page{

    private final static AccountData accountData = ConfigFactory.create(AccountData.class, System.getenv());


    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/TextInputLayout/android.widget.FrameLayout/android.widget.EditText")
    private WebElement email;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.EditText")
    private WebElement password;

    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_ali_sign_in_btn")
    private WebElement signInBtn;

    @FindBy(id = "android:id/message")
    private WebElement message;

    public WebElement getMessage() {
        return message;
    }

    public SignInPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public SignInPage enterValidEmail() {
        email.sendKeys(accountData.login());
        return new SignInPage(driver);
    }

    public SignInPage enterInvalidEmail() {
        email.sendKeys("qweqw@gmail.com");
        return new SignInPage(driver);
    }

    public SignInPage enterValidPassword() {
        password.sendKeys(accountData.password());
        return new SignInPage(driver);
    }

    public SignInPage enterInvalidPassword() {
        password.sendKeys("abcasdfsadf");
        return new SignInPage(driver);
    }

    public <T extends Page> T clickSignInButtonOnSignInPage() throws InterruptedException {
        signInBtn.click();
        Thread.sleep(10000);
        try {
            if(message.isDisplayed()){
                return (T) new SignInPage(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) new AccountPage(driver);
    }
}
