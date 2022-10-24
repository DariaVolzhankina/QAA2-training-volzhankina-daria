package page;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс в котором происходит взаимодействие со страницей настроек аккаунта
 */
@Data
public class AccountPage extends Page{

    public AccountPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Элемент вызова меню
     */
    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    private WebElement hamburger;
}
