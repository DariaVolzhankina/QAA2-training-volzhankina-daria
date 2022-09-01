package objects;

import exceptions.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

import java.util.Objects;
import java.util.Scanner;

@Data
@Slf4j
public class ATM {
    private final Currencies currency;
    private final Banks bank;
    private int limit;


    public ATM(Banks bank, Currencies currency, int limit) {
        this.currency = Objects.requireNonNull(currency);
        this.bank = Objects.requireNonNull(bank);
        this.limit = limit;
    }

    public boolean checkBank(Card card) throws WrongBankException {

        if (!bank.equals(card.getBank())) {
            log.warn("Card of another bank");
            throw new WrongBankException("Card of another bank");
        }

        log.info("check bank was successful");
        return bank.equals(card.getBank());
    }

    public boolean checkPinCode(Card card, String pin) {
        if (pin == null) {
            log.warn("pin code cannot be null");
        }

        if (pin.contains(" ") || card.getPinCode().contains(" ")) {
            throw new WrongPinCodeException("pin code cannot contain a whitespace");
        }

        if (pin.trim().equals("") || card.getPinCode().trim().equals("")) {
            log.warn("pin code cannot be empty");
            throw new WrongPinCodeException("pin code cannot be empty");
        }

        if (!card.getPinCode().equals(pin)) {
            log.warn("invalid pin code");
            throw new WrongPinCodeException("invalid pin code");
        }

        log.info("check pin code was successful");
        return card.getPinCode().equals(pin);
    }

    public boolean checkCurrency(Card card) throws WrongCurrencyException {

        if (!currency.equals(card.getCurrency())) {
            log.warn("The ATM issues another currency");
            throw new WrongCurrencyException("The ATM issues another currency");
        }

        log.info("check currency was successful");
        return currency.equals(card.getCurrency());
    }


    public boolean chooseAction(Scanner s, Card card, Cash cash, boolean b1) {
        int choice = s.nextInt();
        switch (choice) {
            case 1:
                int sum = s.nextInt();
                try {
                    card.withdrawMoney(this, sum);
                } catch (MoneyAmountException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    card.putMoney(this, cash);
                } catch (MoneyAmountException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                b1 = false;
                break;
            default:
                throw new WrongActionException("you entered the wrong number");
        }
        return b1;
    }
}

