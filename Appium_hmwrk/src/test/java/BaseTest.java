import config.AndroidSettingsConfig;
import dict.AndroidCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.Getter;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;

public class BaseTest {

    private final static AndroidSettingsConfig androidConfig = ConfigFactory.create(AndroidSettingsConfig.class, System.getenv());

    @Getter
    protected AndroidDriver driver;

    @SneakyThrows
    @BeforeMethod
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidConfig.deviceName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, androidConfig.platformName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidConfig.platformVersion());
        capabilities.setCapability(MobileCapabilityType.UDID, androidConfig.udid());
        capabilities.setCapability(MobileCapabilityType.APP, androidConfig.app());
       //capabilities.setCapability(AndroidCapabilityType.APP_WAIT_ACTIVITY, androidConfig.appWaitActivity());

        driver = new AndroidDriver(new URL(androidConfig.url()),capabilities);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
