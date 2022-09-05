package CreditCardTests;

import exceptions.MoneyAmountException;
import objects.Cash;
import objects.CreditCard;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

public class WithdrawMoneyCredit {

    @DataProvider
    public Object[][] withdrawMoneyCreditPositiveData() {
        return new Object[][]{
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000,10000,10001), 1, new Cash(1,RUB)},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000,10000,10001), 9999, new Cash(9999,RUB)},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000,10000,10001), 10000, new Cash(10000,RUB)},
        };
    }

    @DataProvider
    public Object[][] sumGreaterThanMoneyAmount() {
        return new Object[][]{
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000,10000,10001), 10001,0,9999},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 1,10000,10001), 10001,0,0},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 0,10000,10001), 10000,0,0},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 0,10000,10001), 9999,0,1}
        };
    }

    @DataProvider
    public Object[][] sumLessThanMoneyAmount() {
        return new Object[][]{
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000,10000,10001), 9999,1,10000},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 1,10000,10001), 1,0,10000}
        };
    }

    @DataProvider
    public Object[][] withdrawMoneyCreditNegativeData() {
        return new Object[][]{
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000,10000,10001), 20001},
                {new CreditCard(SBER, "1111222233334444", "1234", RUB, 0,1,10001), 2}
        };
    }

    @Test(dataProvider = "withdrawMoneyCreditPositiveData")
    public void testWithdrawMoneyCreditPositive(CreditCard card, int sum, Cash expected) {
        Assert.assertEquals(card.withdrawMoney(sum), expected);
    }

    @Test(dataProvider = "sumGreaterThanMoneyAmount")
    public void testWithdrawMoneySumGreaterThanMoneyAmount(CreditCard card, int sum, int expectedMoneyAmount, int expectedCreditLimit) {
        card.withdrawMoney(sum);
        Assert.assertEquals(card.getMoneyAmount(), expectedMoneyAmount);
        Assert.assertEquals(card.getCreditLimit(), expectedCreditLimit);
    }

    @Test(dataProvider = "sumLessThanMoneyAmount")
    public void testWithdrawMoneySumLessThanMoneyAmount(CreditCard card, int sum, int expectedMoneyAmount, int expectedCreditLimit) {
        card.withdrawMoney(sum);
        Assert.assertEquals(card.getMoneyAmount(), expectedMoneyAmount);
        Assert.assertEquals(card.getCreditLimit(), expectedCreditLimit);
    }

    @Test(dataProvider = "withdrawMoneyCreditNegativeData", expectedExceptions = MoneyAmountException.class, expectedExceptionsMessageRegExp = "not enough money on the card")
    public void testWithdrawMoneyCreditNegative(CreditCard card, int sum) throws MoneyAmountException{
        card.withdrawMoney(sum);
    }
}
