package ATMtests;

import objects.ATM;
import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WithdrawMoneyDebit {
    @DataProvider
    public Object[][] withdrawMoneyDebitPositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "1234", "rub", 10000), 1, new Cash(1,"rub")},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 10000), 9999, new Cash(9999,"rub")},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 10000), 10000, new Cash(10000,"rub")}
        };
    }

    @DataProvider
    public Object[][] atmLimitAfterMoneyWithdrawalPositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "1234", "rub", 10000), 1, 99999},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 200000), 100000, 0},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 200000), 99999, 1}
        };
    }

    @DataProvider
    public Object[][] moneyAmountAfterMoneyWithdrawalPositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "1234", "rub", 10000), 1, 9999},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 10000), 9999, 1},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 10000), 10000, 0}
        };
    }

    @Test(dataProvider = "withdrawMoneyDebitPositiveData", description = "Подходящий пин-код")
    public void testWithdrawMoneyDebitPositive(ATM atm, DebitCard card, int sum, Cash expected) {
        Assert.assertEquals(atm.withdrawMoney(card,sum), expected);
    }

    @Test(dataProvider = "atmLimitAfterMoneyWithdrawalPositiveData", description = "Подходящий пин-код")
    public void testAtmLimitAfterMoneyWithdrawalPositive(ATM atm, DebitCard card, int sum, int atmLimitExpected ) {
        atm.withdrawMoney(card,sum);
        Assert.assertEquals(atm.getLimit(),atmLimitExpected);
    }

    @Test(dataProvider = "moneyAmountAfterMoneyWithdrawalPositiveData", description = "Подходящий пин-код")
    public void testCardMoneyAmountAfterMoneyWithdrawalPositive(ATM atm, DebitCard card, int sum, int moneyAmountExpected ) {
        atm.withdrawMoney(card,sum);
        Assert.assertEquals(card.getMoneyAmount(),moneyAmountExpected);
    }
}
