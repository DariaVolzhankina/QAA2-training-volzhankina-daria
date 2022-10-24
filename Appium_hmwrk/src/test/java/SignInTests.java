import io.qameta.allure.*;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.*;
import utils.Listener;


/**
 * Класс с проверками полей страницы авторизации
 */
@Epic("Авторизация")
@Feature("Авторизация с разными данными")
@Listeners(Listener.class)
public class SignInTests extends BaseTest {

    @Description("Авторизация с существующими в базе логином/паролем")
    @Severity(SeverityLevel.CRITICAL)
    @SneakyThrows
    @Test(description = "Авторизация с существующими в базе логином/паролем")
    public void correctDataTest() {
        Page page = new MainPage(driver)
                .clickHamburger()
                .clickAccount()
                .clickSignInButton()
                .enterValidEmail()
                .enterValidPassword()
                .clickSignInButtonOnSignInPage();

        Assert.assertTrue(page instanceof AccountPage);
    }

    @Description("Авторизация с некорректным логином")
    @Severity(SeverityLevel.CRITICAL)
    @SneakyThrows
    @Test(description = "Не успешная авторизация. Несуществующий емаил")
    public void incorrectEmailTest() {

        Page page = new MainPage(driver)
                .clickHamburger()
                .clickAccount()
                .clickSignInButton()
                .enterInvalidEmail()
                .enterValidPassword()
                .clickSignInButtonOnSignInPage();

        Assert.assertTrue(page instanceof SignInPage);
        Assert.assertEquals(((SignInPage) page).getMessageWindow().getText(), "Account does not exist. Please register an account to start shopping.");
    }

    @Description("Авторизация с некорректным паролем")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Не успешная авторизация. Неверный пароль.")
    @SneakyThrows
    public void incorrectPasswordTest() {
        Page page = new MainPage(driver)
                .clickHamburger()
                .clickAccount()
                .clickSignInButton()
                .enterValidEmail()
                .enterInvalidPassword()
                .clickSignInButtonOnSignInPage();

        Assert.assertTrue(page instanceof SignInPage);
        Assert.assertEquals(((SignInPage) page).getMessageWindow().getText(), "Password is incorrect. Please try again.");
    }
}
