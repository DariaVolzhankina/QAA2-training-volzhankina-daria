package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {
    /**
     * Ожидает в течении 10 секунд кликабельность элемента
     *
     * @param driver  экземпляр драйвера браузера
     * @param element элемент
     */
    public static void waitUntilElementToBeClickable(final AndroidDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Ожидает в течении 10 секунд что определенный текст присутсвует в параметре value элемента
     *
     * @param driver  экземпляр драйвера браузера
     * @param element элемент
     * @param text    ожидаемый текст
     */
    public static void waitUntilTextToBePresentInElementValue(final AndroidDriver driver, WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }
}
