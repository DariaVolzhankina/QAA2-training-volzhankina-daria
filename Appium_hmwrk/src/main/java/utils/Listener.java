package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import static utils.ScreenHelper.makeScreenshotOnFailure;


public class Listener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        AndroidDriver driver = (AndroidDriver) context.getAttribute("AndroidDriver");
        makeScreenshotOnFailure(driver);
    }
}