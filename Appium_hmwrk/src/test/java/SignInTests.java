import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static utils.Waiters.waitUntilElementToBeClickable;


public class SignInTests extends BaseTest {

    @Test
    @SneakyThrows
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

    @Test
    @SneakyThrows
    public void incorrectEmailTest() {
        Page page = new MainPage(driver)
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
        Page page = new MainPage(driver)
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

    @Test
    @SneakyThrows
    public void swipeTest() {
        MenuPage menuPage = new MainPage(driver).swipe();
        Thread.sleep(5000);
        boolean b = menuPage.getMenu().isDisplayed();
        Assert.assertTrue(b);
    }

    @Test
    @SneakyThrows
    public void switchTest() {

        //Swipe
        MenuPage menuPage = new MainPage(driver).swipe();
        waitUntilElementToBeClickable(driver,menuPage.getSettings());

        //click on Settings
        SettingsPage settingsPage = menuPage.clickSettings();
        waitUntilElementToBeClickable(driver,settingsPage.getNotificationSettings());

        //click on Notification Settings
        NotificationSettingsPage notificationSettingsPage1 = settingsPage.clickNotificationSettings();
        waitUntilElementToBeClickable(driver, notificationSettingsPage1.getSwitchPromotion());

        //turn off notifications
        NotificationSettingsPage notificationSettingsPage2 = notificationSettingsPage1.clickSwitchPromotion();
        boolean off = notificationSettingsPage2.getSwitchPromotion().getText().equals("OFF");
        Assert.assertTrue(off);

        //turn on notifications
        NotificationSettingsPage notificationSettingsPage3 = notificationSettingsPage2.clickSwitchPromotion();
        waitUntilElementToBeClickable(driver, notificationSettingsPage3.getSwitchPromotionNotifications());
        boolean on = notificationSettingsPage3.getSwitchPromotion().getText().equals("ON");
        boolean switchPromotionNotificationsIsDisplayed = notificationSettingsPage3.getSwitchPromotionNotifications().isDisplayed();
        Assert.assertTrue(on);
        Assert.assertTrue(switchPromotionNotificationsIsDisplayed);
    }
}
