import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.*;

import static utils.Waiters.waitUntilElementToBeClickable;

public class TestWithAuthorizedAccount extends BaseTest {

    @SneakyThrows
    @BeforeMethod
    public void login() {
        new MainPage(driver)
                .clickHamburger();
        waitUntilElementToBeClickable(driver, new MenuPage(driver).getAccount());

        new MenuPage(driver).clickAccount();
        waitUntilElementToBeClickable(driver, new LoginPage(driver).getSignInButton());

        new LoginPage(driver).clickSignInButton()
                .enterValidEmail()
                .enterValidPassword()
                .clickSignInButtonOnSignInPage();
    }

    @Test
    @SneakyThrows
    public void swipeTest() {
        //Swipe
        MenuPage menuPage = new AccountPage(driver).swipe();
        waitUntilElementToBeClickable(driver, menuPage.getAccount());

        Assert.assertTrue(menuPage.getMenu().isDisplayed());
    }

    @Test
    @SneakyThrows
    public void turnOnOffNotificationsTest() {

        //Swipe
        MenuPage menuPage = new AccountPage(driver).swipe();
        waitUntilElementToBeClickable(driver, menuPage.getSettings());

        //click on Settings
        SettingsPage settingsPage = menuPage.clickSettings();
        waitUntilElementToBeClickable(driver, settingsPage.getNotificationSettings());

        //click on Notification Settings
        NotificationSettingsPage notificationSettingsPage1 = settingsPage.clickNotificationSettings();
        waitUntilElementToBeClickable(driver, notificationSettingsPage1.getSwitchPromotion());

        //turn off notifications
        NotificationSettingsPage notificationSettingsPage2 = notificationSettingsPage1.clickSwitchPromotion();
        Assert.assertEquals(notificationSettingsPage2.getSwitchPromotion().getText(), "OFF");

        //turn on notifications
        NotificationSettingsPage notificationSettingsPage3 = notificationSettingsPage2.clickSwitchPromotion();
        waitUntilElementToBeClickable(driver, notificationSettingsPage3.getSwitchPromotionNotifications());
        boolean switchPromotionNotificationsIsDisplayed = notificationSettingsPage3.getSwitchPromotionNotifications().isDisplayed();
        Assert.assertEquals(notificationSettingsPage3.getSwitchPromotion().getText(), "ON");
        Assert.assertTrue(switchPromotionNotificationsIsDisplayed);
    }

    @Test
    @SneakyThrows
    public void reminderFrequencyTest() {

        //Swipe
        MenuPage menuPage = new AccountPage(driver).swipe();
        waitUntilElementToBeClickable(driver, menuPage.getSettings());

        //click on Settings
        SettingsPage settingsPage = menuPage.clickSettings();
        waitUntilElementToBeClickable(driver, settingsPage.getNotificationSettings());

        //click on Notification Settings
        NotificationSettingsPage notificationSettingsPage1 = settingsPage.clickNotificationSettings();

        //click on reminder frequency
        NotificationSettingsPage notificationSettingsPage2 = notificationSettingsPage1.swipeVertical().reminderFrequencyClick();
        waitUntilElementToBeClickable(driver,notificationSettingsPage2.getEvery3Days());

        //click on every3Days
        NotificationSettingsPage notificationSettingsPage3 = notificationSettingsPage2.every3DaysClick();
        Assert.assertEquals(notificationSettingsPage3.getReminderFrequencyText().getText(), "Every 3 days");
    }

    @Test
    public void googlePlayTest() {

        //Swipe
        MenuPage menuPage = new AccountPage(driver).swipe();
        waitUntilElementToBeClickable(driver, menuPage.getSettings());

        //click on Settings
        SettingsPage settingsPage = menuPage.clickSettings();
        waitUntilElementToBeClickable(driver, settingsPage.getNotificationSettings());


    }
}
