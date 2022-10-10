import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.SneakyThrows;
import org.testng.ITestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import static utils.DriverFactory.createDriver;

/**
 * Общий класс с настройками всех типов
 */
public class BaseTest implements ITestListener {

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
        driver = createDriver();
    }

    /**
     * Общие настройки для всех тестов, выполняется после каждого тестового метода
     */
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
