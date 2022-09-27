import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AccountPage;
import page.MainPage;
import page.Page;
import page.SignInPage;


public class SignInTests extends BaseTest {

    @Test
    @SneakyThrows
    public void correctDataTest() {
        Page page =  new MainPage(driver)
                .clickHamburger()
                .clickAccount()
                .clickSignInButton()
                .enterValidEmail()
                .enterValidPassword()
                 .clickSignInButtonOnSignInPage();

        Assert.assertTrue(page instanceof AccountPage);
    }

    @Test
    @SneakyThrows
    public void incorrectEmailTest() {
        Page page =  new MainPage(driver)
                .clickHamburger()
                .clickAccount()
                .clickSignInButton()
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
        Page page =  new MainPage(driver)
                .clickHamburger()
                .clickAccount()
                .clickSignInButton()
                .enterValidEmail()
                .enterInvalidPassword()
                .clickSignInButtonOnSignInPage();

        Assert.assertTrue(page instanceof SignInPage);
        boolean b = ((SignInPage) page).getMessage().getText().equals("Password is incorrect. Please try again.");
        Assert.assertTrue(b);
    }
}
