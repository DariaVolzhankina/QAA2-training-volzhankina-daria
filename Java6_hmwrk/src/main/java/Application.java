import exceptions.MoneyAmountException;
import exceptions.WrongBankException;
import exceptions.WrongCurrencyException;
import exceptions.WrongPinCodeException;
import objects.*;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ATM atm = new ATM("Sber", "rub", 100000);
        CreditCard card = new CreditCard("Sber", "1111222233334444", "1234", "rub", 10000,10000);
        Cash cash = new Cash(1000, "rub");

        boolean continueApp1 = true;
        boolean continueApp2 = true;
        boolean checkBank = atm.checkBank(card);

        while (continueApp1) {
            if (checkBank) {
                Scanner s = new Scanner(System.in);
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

                boolean checkPinCode = atm.checkPinCode(card, pin);

                try {
                    if (!checkPinCode) {
                        continueApp2 = false;
                        System.out.println("invalid pin code");
                        throw new WrongPinCodeException("invalid pin code");
                    } else {
                        continueApp2 = true;
                    }
                } catch (WrongPinCodeException e) {
                    e.printStackTrace();
                }

                boolean checkCurrency = atm.checkCurrency(card);

                try {
                    if (!checkCurrency) {
                        System.out.println("The objects.ATM only issues " + atm.getCurrency() + ". insert another card into the ATM");
                        throw new WrongCurrencyException("The objects.ATM only issues " + atm.getCurrency());
                    }
                } catch (WrongCurrencyException e) {
                    e.printStackTrace();
                    continueApp1 = false;
                    continueApp2 = false;
                }

                while (continueApp2) {
                    System.out.println("Choose an action: 1 - withdraw money, 2 - put money, 3 - exit");
                    int choise = s.nextInt();

                    switch (choise) {
                        case 1:
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
                                if (sum < 0) {
                                    System.out.println("the amount of money cannot be a negative value");
                                    sum = 0;
                                    throw new MoneyAmountException("the amount of money cannot be a negative value");
                                }
                            } catch (MoneyAmountException e) {
                                e.printStackTrace();
                            }

                            try {
                                if (sum > (card.getMoneyAmount() + card.getCreditLimit())) {
                                    sum = 0;
                                    throw new MoneyAmountException("not enough money on the card");
                                }
                            } catch (MoneyAmountException e) {
                                e.printStackTrace();
                                System.out.println(("not enough money on the card"));
                            }

                            try {
                                if (sum > atm.getLimit()) {
                                    sum = 0;
                                    throw new MoneyAmountException("The ATM issues an amount up to" + atm.getLimit());
                                }
                            } catch (MoneyAmountException e) {
                                e.printStackTrace();
                                System.out.println("The ATM issues an amount up to " + atm.getLimit());
                            }

                            atm.withdrawMoney(card, sum);
                            System.out.println(card.getMoneyAmount() + card.getCreditLimit());
                            break;
                        case 2:
                            try {
                                if (cash.getSum() == 0) {
                                    throw new MoneyAmountException("no amount of money entered");
                                }
                            } catch (MoneyAmountException e) {
                                e.printStackTrace();
                                System.out.println("no amount of money entered");
                            }

                            try {
                                if (cash.getSum() < 0) {
                                    throw new MoneyAmountException("the amount cannot be less than zero");
                                }
                            } catch (MoneyAmountException e) {
                                e.printStackTrace();
                                System.out.println("the amount cannot be less than zero");
                            }

                            try {
                                if (!atm.getCurrency().equals(cash.getCurrency())) {
                                    throw new WrongCurrencyException("The objects.ATM only issues " + atm.getCurrency());
                                }
                            } catch (WrongCurrencyException e) {
                                e.printStackTrace();
                                System.out.println("The objects.ATM only issues " + atm.getCurrency());
                            }

                            atm.putMoney(card, cash);
                            System.out.println(card.getMoneyAmount());
                            break;
                        case 3:
                            continueApp1 = false;
                            continueApp2 = false;
                            break;
                        default:
                            System.out.println("You entered the wrong number");
                    }
                }
            } else {
                try {
                    System.out.println("Insert a " + atm.getBank() + " bank card");
                    throw new WrongBankException("Insert insert another card into the ATM");
                } catch (WrongBankException e) {
                    e.printStackTrace();
                    continueApp1 = false;
                }
            }
        }
    }
}
