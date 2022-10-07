import io.qameta.allure.*;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.*;

import static io.qameta.allure.Allure.step;
import static utils.Waiters.*;

/**
 * Класс с тестами для авторизаванного аккаунта
 */
@Epic("Тесты с авторизованным аккаунтом")
public class TestWithAuthorizedAccount extends BaseTest {

    @SneakyThrows
    @BeforeMethod
    public void login() {
        new MainPage(driver).clickHamburger();
        waitUntilElementToBeClickable(driver, new MenuPage(driver).getAccount());

        new MenuPage(driver).clickAccount();
        waitUntilElementToBeClickable(driver, new LoginPage(driver).getSignInButton());

        new LoginPage(driver).clickSignInButton()
                .enterValidEmail()
                .enterValidPassword()
                .clickSignInButtonOnSignInPage();
    }

    @Description("Проверка горизонтального свайпа")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Открытие бокового меню свайпом")
    public void swipeTest() {

        step("Свайп", () -> {
            MenuPage menuPage = new AccountPage(driver).swipe();
            waitUntilElementToBeClickable(driver, menuPage.getAccount());
            Assert.assertTrue(menuPage.getMenu().isDisplayed());
        });
    }

    @Description("Включение/выключение")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Включение/Выключение свитча \"Промоакции и скидки\"")
    public void turnOnOffNotificationsTest() {

        step("Свайп", () -> {
            new AccountPage(driver).swipe();
        });

        step("Клик на настройки", () -> {
            MenuPage menuPage = new MenuPage(driver);
            waitUntilElementToBeClickable(driver, menuPage.getSettings());
            menuPage.clickSettings();
        });

        step("Клик на настройки уведомлений", () -> {
            SettingsPage settingsPage = new SettingsPage(driver);
            waitUntilElementToBeClickable(driver, settingsPage.getNotificationSettings());
            settingsPage.clickNotificationSettings();
        });

        step("Выключить уведомления", () -> {
            NotificationSettingsPage notificationSettingsPage1 = new NotificationSettingsPage(driver);
            waitUntilElementToBeClickable(driver, notificationSettingsPage1.getSwitchPromotion());
            NotificationSettingsPage notificationSettingsPage2 = notificationSettingsPage1.clickSwitchPromotion();
            Assert.assertEquals(notificationSettingsPage2.getSwitchPromotion().getText(), "OFF");
        });

        step("Включить уведомления", () -> {
            NotificationSettingsPage notificationSettingsPage = new NotificationSettingsPage(driver).clickSwitchPromotion();
            waitUntilElementToBeClickable(driver, notificationSettingsPage.getSwitchPromotionNotifications());
            boolean switchPromotionNotificationsIsDisplayed = notificationSettingsPage.getSwitchPromotionNotifications().isDisplayed();
            Assert.assertEquals(notificationSettingsPage.getSwitchPromotion().getText(), "ON");
            Assert.assertTrue(switchPromotionNotificationsIsDisplayed);
        });
    }

    @Description(" Включение радиокнопки “Каждые 3 дня” в настройке \"Частота напоминаний\"")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Включение радиокнопки “Каждые 3 дня” в настройке \"Частота напоминаний\"")
    public void reminderFrequencyTest() {

        step("Свайп", () -> {
            new AccountPage(driver).swipe();
        });

        step("Клик на настройки", () -> {
            MenuPage menuPage = new MenuPage(driver);
            waitUntilElementToBeClickable(driver, menuPage.getSettings());
            menuPage.clickSettings();
        });

        step("Клик на настройки уведомлений", () -> {
            SettingsPage settingsPage = new SettingsPage(driver);
            waitUntilElementToBeClickable(driver, settingsPage.getNotificationSettings());
            settingsPage.clickNotificationSettings();
        });

        step("Клик на частоту напоминаний", () -> {
            new NotificationSettingsPage(driver)
                    .swipeVertical()
                    .reminderFrequencyClick();
        });

        step("Клик на каждые 3 дня", () -> {
            NotificationSettingsPage notificationSettingsPage1 = new NotificationSettingsPage(driver);
            waitUntilElementToBeClickable(driver, notificationSettingsPage1.getEvery3Days());
            NotificationSettingsPage notificationSettingsPage2 = notificationSettingsPage1.every3DaysClick();
            Assert.assertEquals(notificationSettingsPage2.getReminderFrequencyText().getText(), "Every 3 days");
        });
    }

    @Description("Переход в Google Play ")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Переход в Google Play")
    public void googlePlayTest() {

        step("Свайп", () -> {
            new AccountPage(driver).swipe();
        });

        step("Клик на настройки", () -> {
            MenuPage menuPage = new MenuPage(driver);
            waitUntilElementToBeClickable(driver, menuPage.getSettings());
            menuPage.clickSettings();
        });

        step("Клик на PlayMarket", () -> {
            SettingsPage settingsPage = new SettingsPage(driver);
            waitUntilVisibilityOfElement(driver, settingsPage.getRateInPlayMarket());
            PlayMarketPage playMarketPage = settingsPage.clickRateInPlayMarket();
            waitUntilVisibilityOfElement(driver, playMarketPage.getOnboardingText());
            Assert.assertEquals(playMarketPage.getOnboardingText().getText(), "Sign in to find the latest Android apps, games, movies, music, & more");
        });
    }
}
