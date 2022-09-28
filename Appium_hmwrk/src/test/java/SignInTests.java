import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static utils.Waiters.waitUntilElementToBeClickable;

public class SignInTests extends BaseTest {

    @Test
    @SneakyThrows
    public void correctDataTest() {
        new MainPage(driver).clickHamburger();
        waitUntilElementToBeClickable(driver, new MenuPage(driver).getAccount());

        new MenuPage(driver).clickAccount();
        waitUntilElementToBeClickable(driver, new LoginPage(driver).getSignInButton());

        Page page = new LoginPage(driver).clickSignInButton()
                .enterValidEmail()
                .enterValidPassword()
                .clickSignInButtonOnSignInPage();

        Assert.assertTrue(page instanceof AccountPage);
    }

    @Test
    @SneakyThrows
    public void incorrectEmailTest() {
        new MainPage(driver).clickHamburger();
        waitUntilElementToBeClickable(driver, new MenuPage(driver).getAccount());

        new MenuPage(driver).clickAccount();
        waitUntilElementToBeClickable(driver, new LoginPage(driver).getSignInButton());

        Page page = new LoginPage(driver).clickSignInButton()
                .enterInvalidEmail()
                .enterValidPassword()
                .clickSignInButtonOnSignInPage();

        Assert.assertTrue(page instanceof SignInPage);
        boolean b = ((SignInPage) page).getMessage().getText().equals("Account does not exist. Please register an account to start shopping.");
        Assert.assertTrue(b);
    }

    @Test
    @SneakyThrows
    public void incorrectPasswordTest() {
        new MainPage(driver).clickHamburger();
        waitUntilElementToBeClickable(driver, new MenuPage(driver).getAccount());

        new MenuPage(driver).clickAccount();
        waitUntilElementToBeClickable(driver, new LoginPage(driver).getSignInButton());

        Page page = new LoginPage(driver).clickSignInButton()
                .enterValidEmail()
                .enterInvalidPassword()
                .clickSignInButtonOnSignInPage();

        Assert.assertTrue(page instanceof SignInPage);
        boolean b = ((SignInPage) page).getMessage().getText().equals("Password is incorrect. Please try again.");
        Assert.assertTrue(b);
    }
}
