package ATMtests;

import exceptions.MoneyAmountException;
import exceptions.WrongCurrencyException;
import objects.ATM;
import objects.Cash;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WithdrawMoneyCredit {
    @DataProvider
    public Object[][] withdrawMoneyCreditPositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000,10000), 1, new Cash(1,"rub")},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000,10000), 19999, new Cash(19999,"rub")},
                {new ATM("Sber", "rub", 20000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000,10000), 20000, new Cash(20000,"rub")},
                {new ATM("Sber", "rub", 10000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000,0), 10000, new Cash(10000,"rub")},
                {new ATM("Sber", "rub", 10000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 0,10000), 10000, new Cash(10000,"rub")}
        };
    }

    @DataProvider
    public Object[][] withdrawMoneyCreditNegativeData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 10000),new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000,10000), 10001},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000,10000), 20001},
                {new ATM("Sber", "rub", -100),new CreditCard("Sber", "1111222233334444", "4563", "rub", 100000,100000), 10},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", -10,100000), 1000},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 99999,-10), 1000},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 99999,100000), -10},
                {new ATM("Sber", "rub", 0),new CreditCard("Sber", "1111222233334444", "4563", "rub", 99999,100000), 1000},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 0,0), 100},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000,10000), 0},
        };
    }

    @DataProvider
    public Object[][] withdrawMoneyCreditCheckCurrencyNegativeData() {
        return new Object[][]{
                {new ATM("Sber", "r", 100000),new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000,10000), 1},
                {new ATM("Sber", "", 100000),new CreditCard("Sber", "1111222233334444", "4563", "", 200000,10000), 100000},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub ", 200000,10000), 99999},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", null, 200000,10000), 99999}
        };
    }

    @DataProvider
    public Object[][] atmLimitAfterMoneyWithdrawalPositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000,10000), 1, 99999},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 100000,100000), 100000, 0},
                {new ATM("Sber", "rub", 10000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000,10000), 9999, 1}
        };
    }

    @DataProvider
    public Object[][] moneyAmountAfterMoneyWithdrawalPositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000, 10000), 1, 9999},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000, 10000), 9999, 1},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000, 10000), 10000, 0},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000, 10000), 10001, 0},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000, 10000), 20000, 0}
        };
    }

    @DataProvider
    public Object[][] creditLimitAfterMoneyWithdrawalPositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000, 10000), 1, 10000},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000, 10000), 9999, 10000},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000, 10000), 10000, 10000},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000, 10000), 10001, 9999},
                {new ATM("Sber", "rub", 100000),new CreditCard("Sber", "1111222233334444", "4563", "rub", 10000, 10000), 20000, 0}
        };
    }

    @Test(dataProvider = "withdrawMoneyCreditPositiveData", description = "Подходящий пин-код")
    public void testWithdrawMoneyCreditPositive(ATM atm, CreditCard card, int sum, Cash expected) {
        Assert.assertEquals(atm.withdrawMoney(card,sum), expected);
    }

    @Test(dataProvider = "withdrawMoneyCreditNegativeData", expectedExceptions = MoneyAmountException.class)
    public void testWithdrawMoneyCreditNegative(ATM atm, CreditCard card, int sum) {
        atm.withdrawMoney(card,sum);
    }

    @Test(dataProvider = "withdrawMoneyCreditCheckCurrencyNegativeData", expectedExceptions = WrongCurrencyException.class)
    public void testWithdrawMoneyCreditWrongCurrencyException(ATM atm, CreditCard card, int sum) {
        atm.withdrawMoney(card,sum);
    }

    @Test(dataProvider = "atmLimitAfterMoneyWithdrawalPositiveData", description = "Подходящий пин-код")
    public void testAtmLimitAfterMoneyWithdrawalPositive(ATM atm, CreditCard card, int sum, int atmLimitExpected ) {
        atm.withdrawMoney(card,sum);
        Assert.assertEquals(atm.getLimit(),atmLimitExpected);
    }

    @Test(dataProvider = "moneyAmountAfterMoneyWithdrawalPositiveData", description = "Подходящий пин-код")
    public void testCardMoneyAmountAfterMoneyWithdrawalPositive(ATM atm, CreditCard card, int sum, int moneyAmountExpected ) {
        atm.withdrawMoney(card,sum);
        Assert.assertEquals(card.getMoneyAmount(),moneyAmountExpected);
    }

    @Test(dataProvider = "creditLimitAfterMoneyWithdrawalPositiveData", description = "Подходящий пин-код")
    public void testCardCreditLimitAfterMoneyWithdrawalPositive(ATM atm, CreditCard card, int sum, int creditLimitExpected ) {
        atm.withdrawMoney(card,sum);
        Assert.assertEquals(card.getCreditLimit(),creditLimitExpected);
    }
}
