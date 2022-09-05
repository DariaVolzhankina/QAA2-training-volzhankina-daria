package DebitCardTests;

import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

public class PutMoneyDebitTests {
    @DataProvider
    public Object[][] putMoneyDebitPositiveData() {
        return new Object[][]{
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), new Cash(1,RUB),10001},
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), new Cash(9999,RUB),19999},
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), new Cash(10000,RUB),20000},
        };
    }

    @Test(dataProvider = "putMoneyDebitPositiveData")
    public void testPutMoneyDebitPositive(DebitCard card, Cash sum, int expected) {
        Assert.assertEquals(card.putMoney(sum), expected);
    }
}
