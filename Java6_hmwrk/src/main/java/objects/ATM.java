package objects;

import exceptions.*;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

import java.util.Scanner;
import java.util.regex.Pattern;

@Data
@Slf4j
public class ATM {
    private final Currencies currency;
    private final Banks bank;
    private int limit;


    public ATM(@NonNull Banks bank, @NonNull Currencies currency, int limit) {
        this.currency = currency;
        this.bank = bank;
        this.limit = limit;
    }

    public boolean checkBank(Card card) throws WrongBankException {
        if (bank.equals(card.getBank())) {
            log.info("check bank was successful");
            return bank.equals(card.getBank());
        } else {
            log.warn("Card of another bank");
            throw new WrongBankException("Card of another bank");
        }
    }

    public boolean checkPinCode(Card card, @NonNull String pin) {
        String regex = "^\\d{4}$";

        if (!Pattern.matches(regex, pin) || !card.getPinCode().equals(pin)) {
            throw new WrongPinCodeException("invalid pin code");
        }

        log.info("check pin code was successful");
        return card.getPinCode().equals(pin);
    }

    public boolean checkCurrency(Card card) throws WrongCurrencyException {
        if (currency.equals(card.getCurrency())) {
            log.info("check currency was successful");
            return currency.equals(card.getCurrency());
        } else {
            log.warn("The ATM issues another currency");
            throw new WrongCurrencyException("The ATM issues another currency");
        }
    }

    public int reduceLimit(int sum) {
        int currentLimit = this.getLimit() - sum;
        this.setLimit(currentLimit);
        log.warn("reduceLimit return " + currentLimit);
        return currentLimit;
    }

    public int increaseLimit(int sum) {
        int currentLimit = this.getLimit() + sum;
        this.setLimit(currentLimit);
        log.warn("increaseLimit return " + currentLimit);
        return currentLimit;
    }

    public boolean chooseAction(Scanner s, Card card, Cash cash, boolean b1) {
        // 1 - withdraw, 2 - put, 3 - check money amount, 4 - exit, 5 - check credit limit
        int choice = s.nextInt();
        switch (choice) {
            case 1:
                int sum = s.nextInt();
                if (sum <= 0) {
                    log.warn("the values cannot be equal to zero or less than zero");
                    throw new MoneyAmountException("the values cannot be equal to zero or less than zero");
                } else if (sum > this.getLimit()) {
                    log.warn("ATM doesn't have enough money");
                    throw new MoneyAmountException("ATM doesn't have enough money");
                }
                card.withdrawMoney(this, sum);
                reduceLimit(sum);
                break;
            case 2:
                if (!this.getCurrency().equals(cash.getCurrency())) {
                    log.warn("The objects.ATM only issues " + this.getCurrency());
                    throw new WrongCurrencyException("The objects.ATM only issues " + this.getCurrency());
                } else if (cash.getSum() <= 0) {
                    log.warn("the amount cannot be less than zero or equal to zero ");
                    throw new MoneyAmountException("the amount cannot be less than zero or equal to zero");
                }
                card.putMoney(this, cash);
                increaseLimit(cash.getSum());
                break;
            case 3:
                card.checkMoneyAmount();
                break;
            case 4:
                b1 = false;
                break;
            case 5:
                if (card instanceof CreditCard) {
                    ((CreditCard) card).checkCreditLimit();
                    break;
                } else {
                    throw new WrongActionException("you entered the wrong number");
                }
            default:
                throw new WrongActionException("you entered the wrong number");
        }
        return b1;
    }
}

