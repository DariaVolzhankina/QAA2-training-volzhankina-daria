
package utils;

import accountData.BrowserStack;
import config.AndroidSettingsConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.exceptions.RunTypeException;

import java.net.URL;

/**
 * Класс для манипуляций с драйвером
 */
public class DriverFactory {

    /**
     * Экземпляр конфигурации с данными
     */
    private final static AndroidSettingsConfig androidConfig = ConfigFactory.create(AndroidSettingsConfig.class, System.getenv());

    /**
     * Экземпляр конфигурации с данными
     */
    private final static BrowserStack browserStack = ConfigFactory.create(BrowserStack.class, System.getenv());

    /**
     * Метод для выбора где будут запускаться тесты
     *
     * @return драйвер
     */
    @SneakyThrows
    public static AndroidDriver createDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String runType = androidConfig.runType();

        switch (runType) {
            case ("cloud"):
                capabilities.setCapability(MobileCapabilityType.APP, browserStack.appBrowserStack());
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidConfig.deviceNameBrowserStack());
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidConfig.osVersionBrowserStack());

                return new AndroidDriver(new URL(browserStack.urlBrowserStack()), capabilities);

            case ("local"):
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidConfig.deviceName());
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, androidConfig.platformName());
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidConfig.platformVersion());
                capabilities.setCapability(MobileCapabilityType.UDID, androidConfig.udid());
                capabilities.setCapability(MobileCapabilityType.APP, androidConfig.app());

                return new AndroidDriver(new URL(androidConfig.url()), capabilities);

            default:
                throw new RunTypeException("you entered the wrong number");
        }
    }
}