//package ATMtests;
//
//import exceptions.WrongBankException;
//import exceptions.WrongCurrencyException;
//import objects.ATM;
//import objects.CreditCard;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//public class CheckCurrencyTests {
//    @DataProvider
//    public Object[][] checkCurrencyPositiveData() {
//        return new Object[][]{
//                {new ATM("Sber", "rub", 100000), new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000, 10000), true},
//                {new ATM("Tinkoff", "usd", 100000), new CreditCard("Tinkoff", "1111222233334444", "1234", "usd", 10000, 10000), true}
//        };
//    }
//
//    @DataProvider
//    public Object[][] checkCurrencyNegativeData() {
//        return new Object[][]{
//                {new ATM("Sber", "rub", 100000), new CreditCard("Sber", "1111222233334444", "1234", "eur", 10000, 10000)},
//                {new ATM("Sber", "rub", 100000), new CreditCard("Sber", "1111222233334444", "1234", "rub ", 10000, 10000)},
//                {new ATM("Sber", "еur", 100000), new CreditCard("Sber", "1111222233334444", "1234", "eur", 10000, 10000)},
//                {new ATM("Sber", "", 100000), new CreditCard("Sber", "1111222233334444", "1234", "", 10000, 10000)},
//                {new ATM("Sber", " ", 100000), new CreditCard("Sber", "1111222233334444", "1234", " ", 10000, 10000)},
//                {new ATM("Sber", " ", 100000), new CreditCard("Sber", "1111222233334444", "1234", null, 10000, 10000)}
//        };
//    }
//
//    @Test(dataProvider = "checkCurrencyPositiveData", description = "Подходящая валюта")
//    public void testCheckBankPositive(ATM atm, CreditCard card, boolean expected) {
//        Assert.assertEquals(atm.checkCurrency(card), expected);
//    }
//
//
//    @Test(dataProvider = "checkCurrencyNegativeData",expectedExceptions = WrongCurrencyException.class)
//    public void testCheckBankException(ATM atm, CreditCard card) throws WrongCurrencyException{
//        atm.checkCurrency(card);
//    }
//}
