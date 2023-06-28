import io.qameta.allure.*;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.*;
import utils.Listener;

import static utils.TestDataHelper.swipe;
import static utils.TestDataHelper.swipeVertical;

/**
 * Класс с тестами для авторизаванного аккаунта
 */
@Epic("Тесты с авторизованным аккаунтом")
@Listeners(Listener.class)
public class TestWithAuthorizedAccount extends BaseTest {

    @SneakyThrows
    @BeforeMethod
    public void login() {
        new MainPage(driver)
                .clickHamburger()
                .clickAccount()
                .clickSignInButton()
                .enterValidEmail()
                .enterValidPassword()
                .clickSignInButtonOnSignInPage();
    }

    @Description("Проверка горизонтального свайпа")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Открытие бокового меню свайпом")
    public void swipeTest() {
        swipe(driver, new AccountPage(driver).getHamburger(), new MenuPage(driver).getAccountButton());
    }

    @Description("Включение/выключение")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Включение/Выключение свитча \"Промоакции и скидки\"")
    public void turnOnOffNotificationsTest() {

        swipe(driver, new AccountPage(driver).getHamburger(), new MenuPage(driver).getAccountButton())
                .clickSettings()
                .clickNotificationSettings()
                .clickSwitchPromotion()
                .checkPromotionSwitchIsOff()
                .clickSwitchPromotion()
                .checkPromotionSwitchIsOn();
    }

    @Description(" Включение радиокнопки “Каждые 3 дня” в настройке \"Частота напоминаний\"")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Включение радиокнопки “Каждые 3 дня” в настройке \"Частота напоминаний\"")
    public void reminderFrequencyTest() {

        NotificationSettingsPage page = swipe(driver, new AccountPage(driver).getHamburger(), new MenuPage(driver).getAccountButton())
                .clickSettings()
                .clickNotificationSettings();

        swipeVertical(driver, page)
                .reminderFrequencyClick()
                .every3DaysClick()
                .checkReminderFrequencyTextIsEvery3Days();
    }

    @Description("Переход в Google Play ")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Переход в Google Play")
    public void googlePlayTest() {

        swipe(driver, new AccountPage(driver).getHamburger(), new MenuPage(driver).getAccountButton())
                .clickSettings()
                .clickRateInPlayMarket()
                .checkTextOnPlayMarketPage();
    }
}
