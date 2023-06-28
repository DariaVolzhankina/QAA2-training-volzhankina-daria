package page;

import accountData.AccountData;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Data;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.TestDataHelper.getRandomInvalidPassword;
import static utils.TestDataHelper.INVALID_EMAIL;
import static utils.Waiters.waitUntilElementToBeClickable;
import static utils.Waiters.waitUntilVisibilityOfElement;

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
    @FindBy(id = "com.alibaba.aliexpresshd:id/et_email")
    private WebElement emailField;

    /**
     * Поле ввода пароля
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/et_password")
    private WebElement passwordField;

    /**
     * Кнопка "Войти"
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/rl_ali_sign_in_btn")
    private WebElement signInBtn;

    /**
     * Всплывающее сообщение
     */
    @FindBy(id = "android:id/message")
    private WebElement messageWindow;

    public SignInPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Метод ввода корректного логина
     *
     * @return страницу авторизации
     */
    @Step("Ввод корректного email")
    public SignInPage enterValidEmail() {
        emailField.sendKeys(accountData.login());
        return new SignInPage(driver);
    }

    /**
     * Метод ввода некорректного логина
     *
     * @return страницу авторизации
     */
    @Step("Ввод некорректного email")
    public SignInPage enterInvalidEmail() {
        emailField.sendKeys(INVALID_EMAIL);//Здесь без faker,потому что на различные e-mail приложуха выдает разные сообщения
        return new SignInPage(driver);
    }

    /**
     * Метод ввода корректного пароля
     *
     * @return страницу авторизации
     */
    @Step("Ввод корректного пароля")
    public SignInPage enterValidPassword() {
        passwordField.sendKeys(accountData.password());
        return new SignInPage(driver);
    }

    /**
     * Метод ввода некорректного пароля
     *
     * @return страницу авторизации
     */
    @Step("Ввод некорректного пароля")
    public SignInPage enterInvalidPassword() {
        passwordField.sendKeys(getRandomInvalidPassword());
        return new SignInPage(driver);
    }

    /**
     * Метод клика по кнопке "Войти"
     *
     * @return страницу авторизации или страницу с данными аккаунта
     */
    @Step("Клик на кнопку \"Войти\"")
    public <T extends Page> T clickSignInButtonOnSignInPage() {
        waitUntilElementToBeClickable(driver, signInBtn);
        signInBtn.click();
        try {
            waitUntilVisibilityOfElement(driver, messageWindow);
            if (messageWindow.isDisplayed()) {
                return (T) new SignInPage(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) new AccountPage(driver);
    }
}
