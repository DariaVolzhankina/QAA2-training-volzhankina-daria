package page;

import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.support.PageFactory;

@Data
public class Page {

    /**
     * Переменная с экземпляром драйвера
     */
    protected final AndroidDriver driver;

    public Page(AndroidDriver androidDriver) {
        driver = androidDriver;
        PageFactory.initElements(driver, this);
    }
}
