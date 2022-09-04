package ATMtests;

import exceptions.WrongBankException;
import objects.ATM;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.*;
import static objects.enums.Currencies.*;

public class CheckBankTests {

    @DataProvider
    public Object[][] checkBankPositiveData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000, 10000,10000), true},
                {new ATM(TINKOFF, RUB, 100000), new CreditCard(TINKOFF, "1111222233334444", "1234", RUB, 10000, 10000,10000), true}
        };
    }

    @DataProvider
    public Object[][] checkBankNegativeData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard(TINKOFF, "1111222233334444", "1234", RUB, 10000, 10000,10000)},
                {new ATM(TINKOFF, RUB, 100000), new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000, 10000,10000)}};
    }

    @Test(dataProvider = "checkBankPositiveData", description = "Подходящий банк")
    public void testCheckBankPositive(ATM atm, CreditCard card, boolean expected) {
        Assert.assertEquals(atm.checkBank(card), expected);
    }

    @Test(dataProvider = "checkBankNegativeData",expectedExceptions = WrongBankException.class, expectedExceptionsMessageRegExp = "Card of another bank")
    public void testCheckBankException(ATM atm, CreditCard card) throws WrongBankException{
        atm.checkBank(card);
    }
}
