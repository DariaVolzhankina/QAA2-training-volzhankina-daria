package objects;

import exceptions.MoneyAmountException;
import exceptions.WrongBankException;
import exceptions.WrongCurrencyException;
import exceptions.WrongPinCodeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ATM {
    private final Currencies currency;
    private final Banks bank;
    private int limit;


    public ATM(Banks bank, Currencies currency, int limit) {
        this.currency = currency;
        this.bank = bank;
        this.limit = limit;
    }

    public boolean checkBank(Card card) throws WrongBankException {
        if (bank == null || card.getBank() == null) {
            log.warn("Bank value cannot be null");
            throw new WrongBankException("Bank value cannot be null");
        }

        if (!bank.equals(card.getBank())) {
            log.warn("Card of another bank");
            throw new WrongBankException("Card of another bank");
        }

        log.info("check bank was successful");
        return bank.equals(card.getBank());
    }

    public boolean checkPinCode(Card card, String pin) {
        if (pin == null || card.getPinCode() == null) {
            log.warn("pin code cannot be null");
        }

        if (pin.contains(" ") || card.getPinCode().contains(" ")){
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

        log.info("checkPinCode return " + card.getPinCode().equals(pin));
        return card.getPinCode().equals(pin);
    }

    public boolean checkCurrency(Card card) throws WrongCurrencyException {
        if (currency == null || card.getCurrency() == null) {
            log.warn("Currency cannot be null");
            throw new WrongCurrencyException("Currency cannot be null");
        }

        if (!currency.equals(card.getCurrency())) {
            log.warn("The ATM issues another currency");
            throw new WrongCurrencyException("The ATM issues another currency");
        }

        log.info("checkCurrency return " + currency.equals(card.getCurrency()));
        return currency.equals(card.getCurrency());
    }

    public Cash withdrawMoney(Card card, int sum) throws MoneyAmountException, WrongCurrencyException {
       return card.withdrawMoney(this, sum);
    }


    public int putMoney(Card card, Cash cash) throws MoneyAmountException, WrongCurrencyException {
        return card.putMoney(this, cash);
    }
}

