package page;

import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    public SignInPage clickSignInButton(){
        signInButton.click();
        return new SignInPage(driver);
    }
}
