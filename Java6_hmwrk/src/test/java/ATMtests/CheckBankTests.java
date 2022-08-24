package ATMtests;

import objects.ATM;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckBankTests {

    @DataProvider
    public Object[][] checkBankPositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000), new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000, 10000), true},
                {new ATM("Tinkoff", "rub", 100000), new CreditCard("Tinkoff", "1111222233334444", "1234", "rub", 10000, 10000), true}
        };
    }

    @DataProvider
    public Object[][] checkBankNegativeData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000), new CreditCard("Sber ", "1111222233334444", "1234", "rub", 10000, 10000), false},
                {new ATM("Sber", "rub", 100000), new CreditCard("Sbep", "1111222233334444", "1234", "rub", 10000, 10000), false},
                {new ATM("Sbеr", "rub", 100000), new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000, 10000), false},
                {new ATM("Tinkoff", "rub", 100000), new CreditCard("", "1111222233334444", "1234", "rub", 10000, 10000), false},
                {new ATM("Tinkoff", "rub", 100000), new CreditCard(" ", "1111222233334444", "1234", "rub", 10000, 10000), false},
                {new ATM(" ", "rub", 100000), new CreditCard(" ", "1111222233334444", "1234", "rub", 10000, 10000), false},
                {new ATM("", "rub", 100000), new CreditCard("", "1111222233334444", "1234", "rub", 10000, 10000), false},
                {new ATM(null, "rub", 100000), new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000, 10000), false}
        };
    }

    @Test(dataProvider = "checkBankPositiveData", description = "Подходящий банк")
    public void testCheckBankPositive(ATM atm, CreditCard card, boolean expected) {
        Assert.assertEquals(atm.checkBank(card), expected);
    }

    @Test(dataProvider = "checkBankNegativeData", description = "Неподходящий банк")
    public void testCheckBankNegative(ATM atm, CreditCard card, boolean expected) {
        Assert.assertEquals(atm.checkBank(card), expected);
    }
}
