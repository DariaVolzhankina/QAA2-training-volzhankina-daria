//package utils;
//
//import com.codeborne.selenide.WebDriverRunner;
//import io.appium.java_client.android.AndroidDriver;
//import io.qameta.allure.Attachment;
//import lombok.experimental.UtilityClass;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//import java.net.URL;
//
//@UtilityClass
//public class ScreenHelper {
//    @Attachment(value = "Screen by fail", type = "image/png")
//    public static byte[] makeScreenshotOnFailure() {
//        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
//    }
//}