package utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


@UtilityClass
public class ScreenHelper {
    @Attachment(value = "Screen by fail", type = "image/png")
    public static byte[] makeScreenshotOnFailure(AndroidDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}