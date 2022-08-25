package ATMtests;

import exceptions.WrongPinCodeException;
import objects.ATM;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckPinCodeTest {
    @DataProvider
    public Object[][] checkPinCodePositiveData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "1234", "rub", 10000), "1234", true},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "4563", "rub", 10000), "4563 ", true}
        };
    }

    @DataProvider
    public Object[][] checkPinCodeNegativeData() {
        return new Object[][]{
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "1235", "rub", 10000), "1234"},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", " 1234", "rub", 10000), "1234"},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "1234", "rub", 10000), "qwer"},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", "", "rub", 10000), ""},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", " ", "rub", 10000), " "},
                {new ATM("Sber", "rub", 100000),new DebitCard("Sber", "1111222233334444", " ", "rub", 10000), null},
        };
    }

    @Test(dataProvider = "checkPinCodePositiveData", description = "Подходящий пин-код")
    public void testPinCodePositive(ATM atm, DebitCard card, String pin, boolean expected) {
        Assert.assertEquals(atm.checkPinCode(card,pin), expected);
    }

    @Test(dataProvider = "checkPinCodeNegativeData",expectedExceptions = WrongPinCodeException.class)
    public void testCheckPinCodeException(ATM atm, DebitCard card, String pin) throws WrongPinCodeException{
        atm.checkPinCode(card,pin);
    }
}
