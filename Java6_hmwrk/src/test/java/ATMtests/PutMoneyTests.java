package ATMtests;

import exceptions.MoneyAmountException;
import exceptions.WrongCurrencyException;
import objects.ATM;
import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PutMoneyTests {
    @DataProvider
    public Object[][] putMoneyPositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "1234", "rub", 10000), new Cash(1,"rub"),10001},
                {new ATM("Sber", "rub", 100),new DebitCard("Sber", "1111222233334444", "4563", "rub", 10000),  new Cash(10000,"rub"),20000}
        };
    }

    @DataProvider
    public Object[][] putMoneyNegativeData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "1234", "rub", 10000), new Cash(0,"rub")},
                {new ATM("Sber", "rub", 100),new DebitCard("Sber", "1111222233334444", "4563", "rub", 10000),  new Cash(-10000,"rub")}
        };
    }

    @DataProvider
    public Object[][] putMoneyCheckCurrencyNegativeData() {
        return new Object[][]{
                {new ATM("Sber", "r", 100000),new DebitCard("Sber", "1111222233334444", "1234", "rub", 10000), new Cash(1,"rub")},
                {new ATM("Sber", "", 100000),new DebitCard("Sber", "1111222233334444", "4563", "", 200000), new Cash(1,"rub")},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub ", 200000), new Cash(1,"rub")},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", null, 200000), new Cash(1,"rub")},
                {new ATM("Sber", " ", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 200000), new Cash(1," ")},
                {new ATM("Sber", "", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 200000), new Cash(1,"")},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 200000), new Cash(1,null)},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 200000), new Cash(1,"rub ")},
                {new ATM("Sber", "rb", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 200000), new Cash(1,"rub")},
        };
    }

    @Test(dataProvider = "putMoneyPositiveData", description = "Подходящий пин-код")
    public void testWithdrawMoneyDebitPositive(ATM atm, DebitCard card, Cash cash, int expected) {
        Assert.assertEquals(atm.putMoney(card,cash), expected);
    }

    @Test(dataProvider = "putMoneyCheckCurrencyNegativeData", expectedExceptions = WrongCurrencyException.class)
    public void testWithdrawMoneyDebitWrongCurrencyException(ATM atm, DebitCard card, Cash cash) {
        atm.putMoney(card,cash);
    }

    @Test(dataProvider = "putMoneyNegativeData", expectedExceptions = MoneyAmountException.class)
    public void testWithdrawMoneyDebitNegative(ATM atm, DebitCard card, Cash cash) {
     atm.putMoney(card,cash);
    }
}
