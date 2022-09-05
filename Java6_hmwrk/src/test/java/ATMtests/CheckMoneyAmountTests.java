package ATMtests;

import objects.ATM;
import objects.Card;
import objects.CreditCard;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Banks.TINKOFF;
import static objects.enums.Currencies.RUB;

public class CheckMoneyAmountTests {
    @DataProvider
    public Object[][] checkMoneyAmountData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000, 20000,20000), 10000},
                {new ATM(TINKOFF, RUB, 100000), new DebitCard(TINKOFF, "1111222233334444", "1234", RUB, 0), 0}
        };
    }

    @Test(dataProvider = "checkMoneyAmountData")
    public void testCheckMoneyAmountPositive(ATM atm, Card card, int expected) {
        Assert.assertEquals(atm.checkMoneyAmount(card), expected);
    }
}
