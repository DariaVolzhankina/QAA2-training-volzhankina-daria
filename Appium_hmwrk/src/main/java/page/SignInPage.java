package page;

import accountData.AccountData;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.TestDataHelper.getRandomInvalidPassword;
import static utils.TestDataHelper.INVALID_EMAIL;

/**
 * Класс в котором происходит взаимодействие со страницей авторизации
 */
@Data
public class SignInPage extends Page {

    /**
     * Экземпляр конфигурации с данными
     */
    private final static AccountData accountData = ConfigFactory.create(AccountData.class, System.getenv());

    /**
     * Поле ввода email
     */
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/TextInputLayout/android.widget.FrameLayout/android.widget.EditText")
    private WebElement email;

    /**
     * Поле ввода пароля
     */
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.EditText")
    private WebElement password;

    /**
     * Кнопка "Войти"
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_ali_sign_in_btn")
    private WebElement signInBtn;

    /**
     * Всплывающее сообщение
     */
    @FindBy(id = "android:id/message")
    private WebElement message;

    public SignInPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Метод ввода корректного логина
     *
     * @return страницу авторизации
     */
    public SignInPage enterValidEmail() {
        email.sendKeys(accountData.login());
        return new SignInPage(driver);
    }

    /**
     * Метод ввода некорректного логина
     *
     * @return страницу авторизации
     */
    public SignInPage enterInvalidEmail() {
        email.sendKeys(INVALID_EMAIL);//Здесь без faker,потому что на различные e-mail приложуха выдает разные сообщения
        return new SignInPage(driver);
    }

    /**
     * Метод ввода корректного пароля
     *
     * @return страницу авторизации
     */
    public SignInPage enterValidPassword() {
        password.sendKeys(accountData.password());
        return new SignInPage(driver);
    }

    /**
     * Метод ввода некорректного пароля
     *
     * @return страницу авторизации
     */
    public SignInPage enterInvalidPassword() {
        password.sendKeys(getRandomInvalidPassword());
        return new SignInPage(driver);
    }

    /**
     * Метод клика по кнопке "Войти"
     *
     * @return страницу авторизации или страницу с данными аккаунта
     */
    public <T extends Page> T clickSignInButtonOnSignInPage() throws InterruptedException {
        signInBtn.click();
        Thread.sleep(10000);
        try {
            if (message.isDisplayed()) {
                return (T) new SignInPage(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) new AccountPage(driver);
    }
}
