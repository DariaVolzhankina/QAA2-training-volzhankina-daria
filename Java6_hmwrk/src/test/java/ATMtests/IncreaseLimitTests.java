package ATMtests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import objects.ATM;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

@Epic("Методы банкомата")
@Feature("Увеличение лимита банкомата")
public class IncreaseLimitTests {

    @DataProvider
    public Object[][] increaseLimitData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), 1, 100001},
                {new ATM(SBER, RUB, 0), 1, 1}
        };
    }

    @Test(dataProvider = "increaseLimitData")
    public void testIncreaseLimit(ATM atm, int sum, int expected) {
        Assert.assertEquals(atm.increaseLimit(sum), expected);
    }
}
