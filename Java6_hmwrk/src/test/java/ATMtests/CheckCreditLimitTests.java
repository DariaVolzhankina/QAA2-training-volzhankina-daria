package ATMtests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import objects.ATM;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

@Epic("Методы банкомата")
@Feature("Просмотр кредитного лимита")
public class CheckCreditLimitTests {

    @DataProvider
    public Object[][] checkCreditLimitData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000, 19999,20000), 19999},
                {new ATM(SBER, RUB, 100000), new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000, 20000,20000), 20000}
        };
    }

    @Test(dataProvider = "checkCreditLimitData")
    public void testCheckCreditLimitPositive(ATM atm, CreditCard card, int expected) {
        Assert.assertEquals(atm.checkCreditLimit(card), expected);
    }
}
