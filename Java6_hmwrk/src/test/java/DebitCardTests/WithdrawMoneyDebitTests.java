package DebitCardTests;

import exceptions.MoneyAmountException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

@Epic("Методы дебетовой карты")
@Feature("Снять деньги с дебетовки")
public class WithdrawMoneyDebitTests {

    @DataProvider
    public Object[][] withdrawMoneyDebitPositiveData() {
        return new Object[][]{
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), 1, new Cash(1,RUB)},
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), 9999, new Cash(9999,RUB)},
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), 10000, new Cash(10000,RUB)},
        };
    }

    @DataProvider
    public Object[][] withdrawMoneyDebitNegativeData() {
        return new Object[][]{
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), 10001}
        };
    }

    @DataProvider
    public Object[][] moneyAmountAfterMoneyWithdrawal() {
        return new Object[][]{
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), 1,9999},
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), 9999,1},
                {new DebitCard(SBER, "1111222233334444", "1234", RUB, 10000), 10000,0}
        };
    }

    @Test(dataProvider = "withdrawMoneyDebitPositiveData")
    public void testWithdrawMoneyDebitPositive(DebitCard card, int sum, Cash expected) {
        Assert.assertEquals(card.withdrawMoney(sum), expected);
    }

    @Test(dataProvider = "withdrawMoneyDebitNegativeData", expectedExceptions = MoneyAmountException.class, expectedExceptionsMessageRegExp = "not enough money on the card")
    public void testWithdrawMoneyDebitNegative(DebitCard card, int sum) throws MoneyAmountException{
        card.withdrawMoney(sum);
    }

    @Test(dataProvider = "moneyAmountAfterMoneyWithdrawal")
    public void testMoneyAmountAfterMoneyWithdrawal(DebitCard card, int sum, int expected) {
        card.withdrawMoney(sum);
        Assert.assertEquals(card.getMoneyAmount(), expected);
    }

}
