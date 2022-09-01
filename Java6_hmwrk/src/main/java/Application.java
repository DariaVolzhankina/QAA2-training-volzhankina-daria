import exceptions.*;
import objects.*;
import objects.enums.Banks;
import objects.enums.Currencies;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ATM atm = new ATM(Banks.SBER, Currencies.RUB, 100000);
        Card card = new CreditCard(Banks.SBER, "1111222233334444", "1234", Currencies.RUB, 10000, 10000, 10001);
        Cash cash = new Cash(1000, Currencies.RUB);

        boolean continueApp1 = true;
        boolean continueApp2 = false;

        while (continueApp1) {
            try {
                atm.checkBank(card);
            } catch (WrongBankException e) {
                e.printStackTrace();
                break;
            }

            try {
                atm.checkCurrency(card);
            } catch (WrongCurrencyException e) {
                e.printStackTrace();
                break;
            }

            Scanner s = new Scanner(System.in);
            String pin = s.nextLine();

            try {
                atm.checkPinCode(card, pin);
                continueApp2 = true;
            } catch (WrongPinCodeException e) {
                e.printStackTrace();
            }

            while (continueApp2) {
                try {
                    continueApp2 = atm.chooseAction(s,card,cash,continueApp2);
                }
                catch (WrongActionException e) {
                    e.printStackTrace();
                }
                continueApp1 = continueApp2;
            }
        }
    }
}
