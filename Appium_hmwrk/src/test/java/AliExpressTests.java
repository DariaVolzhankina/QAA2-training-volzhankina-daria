import lombok.SneakyThrows;
import org.testng.annotations.Test;
import page.MainPage;


public class AliExpressTests extends BaseTest{

    @Test
    @SneakyThrows
    public void test(){
        new MainPage(driver).clickHamburger();
    }
}
