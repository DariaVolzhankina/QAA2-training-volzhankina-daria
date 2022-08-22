import exceptions.MoneyAmountException;
import exceptions.WrongPinCodeException;
import objects.ATM;
import objects.Card;
import objects.Cash;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ATM atm = new ATM("Sber", "rub", 100000);
        Card card = new Card("Sber", "1111222233334444", "1234", "rub", 10000);
        Cash cash = new Cash(1000,"rub");

        boolean checkBank =  atm.checkBank(card);

        if(checkBank){
            Scanner s  = new Scanner(System.in);
            System.out.println("Enter pin code");
            String pin = s.nextLine();

            try {
                if (pin == null) {
                    System.out.println("pin code cannot be null");
                    throw new WrongPinCodeException("pin code cannot be null");
                }
            } catch (WrongPinCodeException e) {
                e.printStackTrace();
            }

            try {
                if (pin.equals("")) {
                    System.out.println("pin code cannot be empty");
                    throw new WrongPinCodeException("pin code cannot be empty");
                }
            } catch (WrongPinCodeException e) {
                e.printStackTrace();
            }

            try {
                if (pin.contains(" ")) {
                    System.out.println("pin code cannot contain a whitespace");
                    throw new WrongPinCodeException("pin code contain a whitespace");
                }
            } catch (WrongPinCodeException e) {
                e.printStackTrace();
            }

            boolean checkCard = atm.checkCard(card, pin);

            if(checkCard){
                System.out.println("Choose an action: 1 - withdraw money, 2 - put money");
                int choise = s.nextInt();
                if (choise == 1) {
                    System.out.println("The amount of money you want to withdraw");
                    int sum = s.nextInt();

                    try {
                        if (sum == 0) {
                            System.out.println("enter the amount of money");
                            throw new MoneyAmountException("no amount of money entered");
                        }
                    } catch (MoneyAmountException e) {
                        e.printStackTrace();
                    }

                    try {
                        if (sum < 0){
                            System.out.println("the amount of money cannot be a negative value");
                            sum = 0;
                            throw new MoneyAmountException("the amount of money cannot be a negative value");
                        }
                    }catch (MoneyAmountException e) {
                        e.printStackTrace();
                    }

                    atm.withdrawMoney(card, sum);
                } else if (choise == 2) {
                    atm.putMoney(card, cash);
                } else {
                    System.out.println("You entered the wrong number");
                }
            }
        }else {
            System.out.println("Insert a "+ atm.getBank() +" bank card");
        }
    }
}
