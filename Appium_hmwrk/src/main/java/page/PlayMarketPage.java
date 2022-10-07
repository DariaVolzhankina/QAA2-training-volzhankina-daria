package page;

import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс в котором происходит взаимодействие с приложением Play Market
 */
@Data
public class PlayMarketPage extends Page{
    public PlayMarketPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Элемент с текстом "Sign in to find the latest Android apps, games, movies, music, & more"
     */
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.TextView")
    private WebElement onboardingText;
}
