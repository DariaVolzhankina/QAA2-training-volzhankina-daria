package page;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.Waiters.waitUntilElementToBeClickable;

/**
 * Класс в котором происходит взаимодействие со страницей выбора логина/регистрации
 */
@Data
public class LoginPage extends Page{

    public LoginPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Кнопка "Войти"
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/btn_sign_in")
    private WebElement signInButton;

    /**
     * Метода клика по кнопке "Войти"
     * @return SignInPage - страница авторизации
     */
    @Step("Клик на кнопку \"Войти\"")
    public SignInPage clickSignInButton(){
        waitUntilElementToBeClickable(driver, signInButton);
        signInButton.click();
        return new SignInPage(driver);
    }
}
