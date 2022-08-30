import exceptions.MoneyAmountException;
import exceptions.WrongBankException;
import exceptions.WrongCurrencyException;
import exceptions.WrongPinCodeException;
import objects.*;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ATM atm = new ATM(Banks.SBER, Currencies.RUB, 100000);
        Card card = new CreditCard(Banks.SBER, "1111222233334444", "1234", Currencies.RUB, 10000,10000,100);
        Cash cash = new Cash(1000, Currencies.RUB);

        boolean continueApp1 = true;
        boolean continueApp2;

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
                continueApp2 = false;
            }

            while (continueApp2) {
                //System.out.println("Choose an action: 1 - withdraw money, 2 - put money, 3 - exit");
                int choice = s.nextInt();
                switch (choice) {
                    case 1:
                        int sum = s.nextInt();
                        try {
                            atm.withdrawMoney(card, sum);
                        } catch (MoneyAmountException e) {
                            e.printStackTrace();
                        }
                        //System.out.println(card.getMoneyAmount() + card.getCreditLimit());
                        break;
                    case 2:
                        try {
                            atm.putMoney(card, cash);
                        } catch (MoneyAmountException e) {
                            e.printStackTrace();
                        }

                        //System.out.println(card.getMoneyAmount());
                        break;
                    case 3:
                        continueApp1 = false;
                        continueApp2 = false;
                        break;
                    default:
                        //System.out.println("You entered the wrong number");
                }
            }
        }
    }
}
