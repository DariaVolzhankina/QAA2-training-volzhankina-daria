package CreditCardTests;

import objects.Cash;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

public class PutMoneyCreditTests {

    @DataProvider
    public Object[][] maxCreditLimitEqualsToCreditLimit() {
        return new Object[][]{
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 0,20000,20000), new Cash(1,RUB), 1},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000,20000,20000), new Cash(9999,RUB), 19999},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 30000,20000,20000), new Cash(10000,RUB), 40000},
        };
    }

    @DataProvider
    public Object[][] requiredAmountLessThanSum() {
        return new Object[][]{
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 0,10000,20000), new Cash(15000,RUB), 20000,5000},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000,19999,20000), new Cash(2,RUB), 20000,10001},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 0,0,20000), new Cash(20001,RUB), 20000,1},
        };
    }

    @DataProvider
    public Object[][] requiredAmountGreaterThanSum() {
        return new Object[][]{
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 1,10000,20000), new Cash(10000,RUB), 20000,1},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000,10000,20000), new Cash(9999,RUB), 19999,10000},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 0,0,20000), new Cash(20000,RUB), 20000,0},
        };
    }

    @Test(dataProvider = "maxCreditLimitEqualsToCreditLimit")
    public void testPutMoneyMaxCreditLimitEqualsToCreditLimit(CreditCard card, Cash cash, int expected) {
        Assert.assertEquals(card.putMoney(cash), expected);
    }

    @Test(dataProvider = "requiredAmountLessThanSum")
    public void testPutMoneyRequiredAmountLessThanSum(CreditCard card, Cash cash, int creditLimitExpected, int moneyAmountExpected) {
        card.putMoney(cash);
        Assert.assertEquals(card.getCreditLimit(), creditLimitExpected);
        Assert.assertEquals(card.getMoneyAmount(), moneyAmountExpected);
    }
    @Test(dataProvider = "requiredAmountGreaterThanSum")
    public void testPutMoneyRequiredAmountGreaterThanSum(CreditCard card, Cash cash, int creditLimitExpected, int moneyAmountExpected) {
        card.putMoney(cash);
        Assert.assertEquals(card.getCreditLimit(), creditLimitExpected);
        Assert.assertEquals(card.getMoneyAmount(), moneyAmountExpected);
    }
}
