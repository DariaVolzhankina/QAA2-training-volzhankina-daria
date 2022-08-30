package objects;

import exceptions.MoneyAmountException;
import exceptions.WrongCurrencyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebitCard extends Card {

    private static final Logger logger = LogManager.getLogger();

    public DebitCard(Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
    }

    @Override
    public Cash withdrawMoney(ATM atm, int sum) throws MoneyAmountException {

        if (sum > atm.getLimit()) {
            logger.warn("The ATM issues an amount up to" + atm.getLimit());
            throw new MoneyAmountException("The ATM issues an amount up to" + atm.getLimit());
        } else if (sum > this.getMoneyAmount()) {
            logger.warn("not enough money on the card");
            throw new MoneyAmountException("not enough money on the card");
        } else if (sum < 0 || atm.getLimit() < 0 || this.getMoneyAmount() < 0) {
            logger.warn("the amount cannot be less than zero");
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (sum == 0) {
            logger.warn("the sum cannot be zero");
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (atm.getLimit() == 0) {
            logger.warn("ATM have no money");
            throw new MoneyAmountException("ATM have no money");
        } else if (this.getMoneyAmount() == 0) {
            logger.warn("You have no money");
            throw new MoneyAmountException("You have no money");
        } else {
            this.setMoneyAmount(this.getMoneyAmount() - sum);
            atm.setLimit(atm.getLimit() - sum);
            logger.info("withdrawMoney return " + new Cash(sum, atm.getCurrency()));
            return new Cash(sum, atm.getCurrency());
        }
    }

    @Override
    public int putMoney(ATM atm, Cash cash) {
        if (cash.getCurrency() == null) {
            logger.warn("Currency cannot be null");
            throw new WrongCurrencyException("Currency cannot be null");
        } else if (cash.getSum() == 0) {
            logger.warn("the sum cannot be zero");
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (cash.getSum() < 0) {
            logger.warn("the amount cannot be less than zero");
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (atm.getCurrency().equals("") || cash.getCurrency().equals("")) {
            logger.warn("Currency cannot be empty");
            throw new WrongCurrencyException("Currency cannot be empty");
        } else if (!atm.getCurrency().equals(cash.getCurrency())) {
            logger.warn("The objects.ATM only issues " + this.getCurrency());
            throw new WrongCurrencyException("The objects.ATM only issues " + this.getCurrency());
        } else {
            this.setMoneyAmount(this.getMoneyAmount() + cash.getSum());
            atm.setLimit(atm.getLimit() + cash.getSum());
            logger.info("putMoney return " + this.getMoneyAmount());
            return this.getMoneyAmount();
        }
    }
}
