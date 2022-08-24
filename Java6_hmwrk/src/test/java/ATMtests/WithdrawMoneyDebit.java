package ATMtests;

import objects.ATM;
import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WithdrawMoneyDebit {
    @Test(dataProvider = "checkPinCodePositiveData", description = "Подходящий пин-код")
    public void testPinCodePositive(ATM atm, DebitCard card, int sum, Cash expected) {
        Assert.assertEquals(atm.withdrawMoney(card,sum), expected);
    }
}
