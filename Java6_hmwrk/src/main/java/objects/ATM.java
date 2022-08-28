package objects;

import exceptions.MoneyAmountException;
import exceptions.WrongBankException;
import exceptions.WrongCurrencyException;
import exceptions.WrongPinCodeException;
import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
@Builder
public class ATM {
    private String currency;
    private String bank;
    private int limit;
    private static final Logger logger = LogManager.getLogger();

    public ATM(String bank, String currency, int limit) {
        this.currency = currency;
        this.bank = bank;
        this.limit = limit;
    }

    public boolean checkBank(Card card) throws WrongBankException {
        if (bank == null || card.getBank() == null) {
            logger.warn("Bank value cannot be null");
            throw new WrongBankException("Bank value cannot be null");
        }

        if (bank.trim().equals("") || card.getBank().trim().equals("")) {
            logger.warn("Bank value cannot be empty");
            throw new WrongBankException("Bank value cannot be empty");
        }

        if (!bank.equals(card.getBank())) {
            logger.warn("Card of another bank");
            throw new WrongBankException("Card of another bank");
        }

        logger.info("checkBank return " + bank.equals(card.getBank()));
        return bank.equals(card.getBank());
    }

    public boolean checkPinCode(Card card, String pin) {
        if (pin == null || card.getPinCode() == null) {
            logger.warn("pin code cannot be null");
            throw new WrongPinCodeException("pin code cannot be null");
        }

        if (pin.trim().equals("") || card.getPinCode().trim().equals("")) {
            logger.warn("pin code cannot be empty");
            throw new WrongPinCodeException("pin code cannot be empty");
        }

        if (!card.getPinCode().equals(pin.trim())) {
            logger.warn("invalid pin code");
            throw new WrongPinCodeException("invalid pin code");
        }

        logger.info("checkPinCode return " + card.getPinCode().equals(pin.trim()));
        return card.getPinCode().equals(pin.trim());
    }

    public boolean checkCurrency(Card card) throws WrongCurrencyException {
        if (currency == null || card.getCurrency() == null) {
            logger.warn("Currency cannot be null");
            throw new WrongCurrencyException("Currency cannot be null");
        }

        if (currency.trim().equals("") || card.getCurrency().trim().equals("")) {
            logger.warn("Currency cannot be empty");
            throw new WrongCurrencyException("Currency cannot be empty");
        }

        if (!currency.equals(card.getCurrency())) {
            logger.warn("The ATM issues another currency");
            throw new WrongCurrencyException("The ATM issues another currency");
        }

        logger.info("checkCurrency return " + currency.equals(card.getCurrency()));
        return currency.equals(card.getCurrency());
    }

    public Cash withdrawMoney(DebitCard card, int sum) throws MoneyAmountException, WrongCurrencyException {

        checkCurrency(card);

        if (sum > limit) {
            logger.warn("The ATM issues an amount up to" + this.getLimit());
            throw new MoneyAmountException("The ATM issues an amount up to" + this.getLimit());
        } else if (sum > card.getMoneyAmount()) {
            logger.warn("not enough money on the card");
            throw new MoneyAmountException("not enough money on the card");
        } else if (sum < 0 || this.getLimit() < 0 || card.getMoneyAmount() < 0) {
            logger.warn("the amount cannot be less than zero");
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (sum == 0) {
            logger.warn("the sum cannot be zero");
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (this.getLimit() == 0) {
            logger.warn("ATM have no money");
            throw new MoneyAmountException("ATM have no money");
        } else if (card.getMoneyAmount() == 0) {
            logger.warn("You have no money");
            throw new MoneyAmountException("You have no money");
        } else {
            card.setMoneyAmount(card.getMoneyAmount() - sum);
            limit -= sum;
            logger.info("withdrawMoney return " + new Cash(sum, this.currency));
            return new Cash(sum, this.currency);
        }
    }

    public Cash withdrawMoney(CreditCard card, int sum) throws MoneyAmountException, WrongCurrencyException {

        checkCurrency(card);

        if (sum > limit) {
            logger.warn("The ATM issues an amount up to" + this.getLimit());
            throw new MoneyAmountException("The ATM issues an amount up to" + this.getLimit());
        } else if (sum < 0 || this.getLimit() < 0 || card.getMoneyAmount() < 0 || card.getCreditLimit() < 0) {
            logger.warn("the amount cannot be less than zero");
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (sum == 0) {
            logger.warn("the sum cannot be zero");
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (this.getLimit() == 0) {
            logger.warn("ATM have no money");
            throw new MoneyAmountException("ATM have no money");
        } else if (card.getMoneyAmount() + card.getCreditLimit() == 0) {
            logger.warn("You have no money");
            throw new MoneyAmountException("You have no money");
        } else if (sum > (card.getMoneyAmount() + card.getCreditLimit())) {
            logger.warn("not enough money on the card");
            throw new MoneyAmountException("not enough money on the card");
        } else {
            if (sum > card.getMoneyAmount()) {
                card.setCreditLimit(card.getMoneyAmount() + card.getCreditLimit() - sum);
                card.setMoneyAmount(0);
            } else {
                card.setMoneyAmount(card.getMoneyAmount() - sum);
            }
            limit -= sum;
            logger.info("withdrawMoney return " + new Cash(sum, this.currency));
            return new Cash(sum, this.currency);
        }
    }


    public int putMoney(Card card, Cash cash) throws MoneyAmountException, WrongCurrencyException {

        checkCurrency(card);

        if (cash.getCurrency() == null) {
            logger.warn("Currency cannot be null");
            throw new WrongCurrencyException("Currency cannot be null");
        } else if (cash.getSum() == 0) {
            logger.warn("the sum cannot be zero");
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (cash.getSum() < 0) {
            logger.warn("the amount cannot be less than zero");
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (currency.trim().equals("") || cash.getCurrency().trim().equals("")) {
            logger.warn("Currency cannot be empty");
            throw new WrongCurrencyException("Currency cannot be empty");
        } else if (!currency.equals(cash.getCurrency())) {
            logger.warn("The objects.ATM only issues " + this.getCurrency());
            throw new WrongCurrencyException("The objects.ATM only issues " + this.getCurrency());
        } else {
            card.setMoneyAmount(card.getMoneyAmount() + cash.getSum());
            limit += cash.getSum();
            logger.info("putMoney return " +  card.getMoneyAmount());
            return card.getMoneyAmount();
        }
    }
}

