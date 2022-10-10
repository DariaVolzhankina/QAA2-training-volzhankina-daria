import io.qameta.allure.*;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static io.qameta.allure.Allure.step;
import static utils.Waiters.waitUntilElementToBeClickable;

/**
 * Класс с проверками полей страницы авторизации
 */
@Epic("Авторизация")
@Feature("Авторизация с разными данными")
public class SignInTests extends BaseTest {

    @Description("Авторизация с существующими в базе логином/паролем")
    @Severity(SeverityLevel.CRITICAL)
    @SneakyThrows
    @Test(description = "Авторизация с существующими в базе логином/паролем")
    public void correctDataTest() {

        step("Клик на меню", () -> {
            new MainPage(driver).clickHamburger();
        });

        step("Клик на аккаунт", () -> {
            waitUntilElementToBeClickable(driver, new MenuPage(driver).getAccount());
            new MenuPage(driver).clickAccount();
        });

        step("Клик на кнопку войти", () -> {
            waitUntilElementToBeClickable(driver, new LoginPage(driver).getSignInButton());
            new LoginPage(driver).clickSignInButton();
        });

        step("Ввод данных", () -> {
            new SignInPage(driver)
                    .enterValidEmail()
                    .enterValidPassword();
        });

        step("Проверка данных", () -> {
            waitUntilElementToBeClickable(driver, new SignInPage(driver).getSignInBtn());
            Page page = new SignInPage(driver).clickSignInButtonOnSignInPage();
            Assert.assertTrue(page instanceof AccountPage);
        });
    }

    @Description("Авторизация с некорректным логином")
    @Severity(SeverityLevel.CRITICAL)
    @SneakyThrows
    @Test(description = "Не успешная авторизация. Несуществующий емаил")
    public void incorrectEmailTest() {

        step("Клик на меню", () -> {
            new MainPage(driver).clickHamburger();
        });

        step("Клик на аккаунт", () -> {
            waitUntilElementToBeClickable(driver, new MenuPage(driver).getAccount());
            new MenuPage(driver).clickAccount();
        });

        step("Клик на кнопку войти", () -> {
            waitUntilElementToBeClickable(driver, new LoginPage(driver).getSignInButton());
            new LoginPage(driver).clickSignInButton();
        });

        step("Ввод данных", () -> {
            waitUntilElementToBeClickable(driver, new SignInPage(driver).getEmail());
            new SignInPage(driver).enterInvalidEmail()
                    .enterValidPassword();
        });

        step("Проверка данных", () -> {
            waitUntilElementToBeClickable(driver, new SignInPage(driver).getSignInBtn());
            Page page = new SignInPage(driver).clickSignInButtonOnSignInPage();

            Assert.assertTrue(page instanceof SignInPage);
            boolean b = ((SignInPage) page).getMessage().getText().equals("Account does not exist. Please register an account to start shopping.");
            Assert.assertTrue(b);
        });
    }

    @Description("Авторизация с некорректным паролем")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Не успешная авторизация. Неверный пароль.")
    @SneakyThrows
    public void incorrectPasswordTest() {
        step("Клик на меню", () -> {
            new MainPage(driver).clickHamburger();
        });

        step("Клик на аккаунт", () -> {
            waitUntilElementToBeClickable(driver, new MenuPage(driver).getAccount());
            new MenuPage(driver).clickAccount();
        });
        step("Клик на кнопку войти", () -> {
            waitUntilElementToBeClickable(driver, new LoginPage(driver).getSignInButton());
            new LoginPage(driver).clickSignInButton();
        });

        step("Ввод данных", () -> {
            new SignInPage(driver).enterValidEmail()
                    .enterInvalidPassword();
        });

        step("Проверка данных", () -> {
            waitUntilElementToBeClickable(driver, new SignInPage(driver).getSignInBtn());
            Page page = new SignInPage(driver).clickSignInButtonOnSignInPage();

            Assert.assertTrue(page instanceof SignInPage);
            boolean b = ((SignInPage) page).getMessage().getText().equals("Password is incorrect. Please try again.");
            Assert.assertTrue(b);
        });
    }
}
