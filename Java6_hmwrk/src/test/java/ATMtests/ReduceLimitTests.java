package ATMtests;

import objects.ATM;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

public class ReduceLimitTests {

    @DataProvider
    public Object[][] reduceLimitData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), 100000, 0},
                {new ATM(SBER, RUB, 100000), 99999, 1}
        };
    }

    @Test(dataProvider = "reduceLimitData")
    public void testReduceLimit(ATM atm, int sum, int expected) {
        Assert.assertEquals(atm.reduceLimit(sum), expected);
    }
}
