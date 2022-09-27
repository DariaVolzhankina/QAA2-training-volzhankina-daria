package page;

import io.appium.java_client.android.AndroidDriver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.support.PageFactory;

@Data
@Builder
public class Page {
    protected final AndroidDriver driver;

    public Page(AndroidDriver androidDriver) {
        driver = androidDriver;
        PageFactory.initElements(driver, this);
    }
}
