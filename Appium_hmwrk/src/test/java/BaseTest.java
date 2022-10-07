import config.AndroidSettingsConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.Getter;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;

/**
 * Общий класс с настройками всех типов
 */
public class BaseTest implements ITestListener {

    /**
     * Экземпляр конфигурации с параметрами устройства
     */
    private final static AndroidSettingsConfig androidConfig = ConfigFactory.create(AndroidSettingsConfig.class, System.getenv());

    /**
     * Переменная с экземпляром драйвера
     */
    @Getter
    protected AndroidDriver driver;

    /**
     * Общие настройки для всех тестов, выполняется перед каждым тестовым методом
     */
    @SneakyThrows
    @BeforeMethod
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidConfig.deviceName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, androidConfig.platformName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidConfig.platformVersion());
        capabilities.setCapability(MobileCapabilityType.UDID, androidConfig.udid());
        capabilities.setCapability(MobileCapabilityType.APP, androidConfig.app());

        driver = new AndroidDriver(new URL(androidConfig.url()),capabilities);
    }

    /**
     * Общие настройки для всех тестов, выполняется после каждого тестового метода
     */
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
