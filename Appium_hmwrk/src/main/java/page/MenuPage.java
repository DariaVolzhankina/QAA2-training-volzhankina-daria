package page;


import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.Waiters.waitUntilElementToBeClickable;

/**
 * Класс в котором происходит взаимодействие со списком меню
 */
@Data
public class MenuPage extends Page{

    public MenuPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Элемент с информацией об аккаунте
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/chosen_account_view")
    private WebElement accountButton;

    /**
     * Элемент со списком меню
     */
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.ScrollView/android.widget.LinearLayout")
    private WebElement menuList;

    /**
     * Элемент настроек
     */
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.ScrollView/android.widget.LinearLayout/" +
            "android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[9]")
    private WebElement settingsButton;

    /**
     * Метод клика по аккаунту
     *
     * @return LoginPage - страница выбора логина/регистрации
     */
    @Step("Клик на аккаунт")
    public LoginPage clickAccount(){
        waitUntilElementToBeClickable(driver, accountButton);
        accountButton.click();
        return new LoginPage(driver);
    }

    /**
     * Метод клика по Настройкам
     *
     * @return SettingsPage - страница с настройками
     */
    @Step("Клик на настройки")
    public SettingsPage clickSettings(){
        waitUntilElementToBeClickable(driver, settingsButton);
        settingsButton.click();
        return new SettingsPage(driver);
    }
}
