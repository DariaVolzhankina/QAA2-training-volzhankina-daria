package ATMtests;

import exceptions.WrongBankException;
import exceptions.WrongCurrencyException;
import objects.ATM;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Banks.TINKOFF;
import static objects.enums.Currencies.RUB;
import static objects.enums.Currencies.USD;

public class CheckCurrencyTests {
    @DataProvider
    public Object[][] checkCurrencyPositiveData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard(SBER, "1111222233334444", "1234", RUB, 10000, 10000,10000), true},
                {new ATM(TINKOFF, USD, 100000), new CreditCard(TINKOFF, "1111222233334444", "1234", USD, 10000, 10000,10000), true}
        };
    }

    @DataProvider
    public Object[][] checkCurrencyNegativeData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard(SBER, "1111222233334444", "1234", USD, 10000, 10000,10000)},
                {new ATM(TINKOFF, USD, 100000), new CreditCard(TINKOFF, "1111222233334444", "1234", RUB, 10000, 10000,10000)}
        };
    }

    @Test(dataProvider = "checkCurrencyPositiveData", description = "Подходящая валюта")
    public void testCheckBankPositive(ATM atm, CreditCard card, boolean expected) {
        Assert.assertEquals(atm.checkCurrency(card), expected);
    }


    @Test(dataProvider = "checkCurrencyNegativeData",expectedExceptions = WrongCurrencyException.class, expectedExceptionsMessageRegExp = "The ATM issues another currency")
    public void testCheckBankException(ATM atm, CreditCard card) throws WrongCurrencyException{
        atm.checkCurrency(card);
    }
}
