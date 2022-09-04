package ATMtests;

import exceptions.MoneyAmountException;
import exceptions.WrongCurrencyException;
import objects.ATM;
import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.*;

public class PutMoneyTests {
    @DataProvider
    public Object[][] putMoneyPositiveData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), new Cash(1, RUB), 10001},
                {new ATM(SBER, RUB, 100000), new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), new Cash(10000, RUB), 20000}
        };
    }

    @DataProvider
    public Object[][] putMoneyMoneyAmountException() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), new Cash(0, RUB)},
                {new ATM(SBER, RUB, 100000), new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), new Cash(-10000, RUB)}
        };
    }

    @DataProvider
    public Object[][] putMoneyWrongCurrencyException() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), new Cash(100, USD)},
                {new ATM(SBER, RUB, 100000), new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), new Cash(100, EUR)}
        };
    }

    @Test(dataProvider = "putMoneyPositiveData")
    public void testWithdrawMoneyDebitPositive(ATM atm, DebitCard card, Cash cash, int expected) {
        Assert.assertEquals(atm.putMoney(card, cash), expected);
    }

    @Test(dataProvider = "putMoneyWrongCurrencyException", expectedExceptions = WrongCurrencyException.class, expectedExceptionsMessageRegExp = "the ATM does not accept this currency")
    public void testWithdrawMoneyDebitWrongCurrencyException(ATM atm, DebitCard card, Cash cash) {
        atm.putMoney(card, cash);
    }

    @Test(dataProvider = "putMoneyMoneyAmountException", expectedExceptions = MoneyAmountException.class, expectedExceptionsMessageRegExp = "the amount cannot be less than zero or equal to zero")
    public void testWithdrawMoneyDebitNegative(ATM atm, DebitCard card, Cash cash) {
        atm.putMoney(card, cash);
    }
}
