package page;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Класс в котором происходит взаимодействие со главной страницей
 */
@Data
public class MainPage extends Page {

    public MainPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Элемент вызова меню
     */
    @FindBy(id = "com.alibaba.aliexpresshd:id/left_action")
    private WebElement hamburger;

    /**
     * Метод вызова меню
     *
     * @return MenuPage - страница меню
     */
    @Step("Клик на иконку меню")
    public MenuPage clickHamburger() {
        hamburger.click();
        return new MenuPage(driver);
    }
}