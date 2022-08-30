package objects;

import exceptions.MoneyAmountException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreditCard extends Card {
    private int creditLimit;
    //private final int maxCreditLimit;
    private static final Logger logger = LogManager.getLogger();

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public CreditCard(Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount, int creditLimit) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
        this.creditLimit = creditLimit;
    }

    @Override
    public Cash withdrawMoney(ATM atm, int sum) throws MoneyAmountException {

        if (sum > atm.getLimit()) {
            logger.warn("The ATM issues an amount up to" + atm.getLimit());
            throw new MoneyAmountException("The ATM issues an amount up to" + atm.getLimit());
        } else if (sum < 0 || atm.getLimit() < 0 || this.getMoneyAmount() < 0 || this.getCreditLimit() < 0) {
            logger.warn("the amount cannot be less than zero");
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (sum == 0) {
            logger.warn("the sum cannot be zero");
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (atm.getLimit() == 0) {
            logger.warn("ATM have no money");
            throw new MoneyAmountException("ATM have no money");
        } else if (this.getMoneyAmount() + this.getCreditLimit() == 0) {
            logger.warn("You have no money");
            throw new MoneyAmountException("You have no money");
        } else if (sum > (this.getMoneyAmount() + this.getCreditLimit())) {
            logger.warn("not enough money on the card");
            throw new MoneyAmountException("not enough money on the card");
        } else {
            if (sum > this.getMoneyAmount()) {
                this.setCreditLimit(this.getMoneyAmount() + this.getCreditLimit() - sum);
                this.setMoneyAmount(0);
            } else {
                this.setMoneyAmount(this.getMoneyAmount() - sum);
            }
            atm.setLimit(atm.getLimit() - sum);
            logger.info("withdrawMoney return " + new Cash(sum, atm.getCurrency()));
            return new Cash(sum, atm.getCurrency());
        }
    }

    @Override
    public int putMoney(ATM atm, Cash cash) {
        return 0;
    }
}
