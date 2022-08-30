package objects;

import exceptions.MoneyAmountException;
import exceptions.WrongCurrencyException;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

@Slf4j
public class CreditCard extends Card {
    private int creditLimit;
    private final int maxCreditLimit;

    public int getMaxCreditLimit() {
        return maxCreditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public CreditCard(Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount, int creditLimit, int maxCreditLimit) throws MoneyAmountException {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
        this.creditLimit = creditLimit;
        this.maxCreditLimit = maxCreditLimit;

        if (maxCreditLimit < creditLimit) {
            throw new MoneyAmountException("Credit limit cannot be greater than the maximum value");
        }
    }

    @Override
    public Cash withdrawMoney(ATM atm, int sum) throws MoneyAmountException {

        if (sum > atm.getLimit()) {
            log.warn("The ATM issues an amount up to" + atm.getLimit());
            throw new MoneyAmountException("The ATM issues an amount up to" + atm.getLimit());
        } else if (sum <= 0 || atm.getLimit() <= 0 || this.getMoneyAmount() < 0 || this.getCreditLimit() < 0) {
            log.warn("the values cannot be equal to zero or less than zero");
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (this.getMoneyAmount() + this.getCreditLimit() == 0) {
            log.warn("You have no money");
            throw new MoneyAmountException("You have no money");
        } else if (sum > (this.getMoneyAmount() + this.getCreditLimit())) {
            log.warn("not enough money on the card");
            throw new MoneyAmountException("not enough money on the card");
        } else {
            if (sum > this.getMoneyAmount()) {
                this.setCreditLimit(this.getMoneyAmount() + this.getCreditLimit() - sum);
                this.setMoneyAmount(0);
            } else {
                this.setMoneyAmount(this.getMoneyAmount() - sum);
            }
            atm.setLimit(atm.getLimit() - sum);
            log.info("withdrawMoney return " + new Cash(sum, atm.getCurrency()));
            return new Cash(sum, atm.getCurrency());
        }
    }

    @Override
    public int putMoney(ATM atm, Cash cash) throws WrongCurrencyException, MoneyAmountException {
        if (cash.getSum() == 0) {
            log.warn("the sum cannot be zero");
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (cash.getSum() < 0) {
            log.warn("the amount cannot be less than zero");
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (!atm.getCurrency().equals(cash.getCurrency())) {
            log.warn("The objects.ATM only issues " + this.getCurrency());
            throw new WrongCurrencyException("The objects.ATM only issues " + this.getCurrency());
        } else {
            if (this.maxCreditLimit > this.creditLimit) {
                int requiredAmount = this.maxCreditLimit - this.creditLimit;
                if (requiredAmount < cash.getSum()) {
                    int remainingAmount = cash.getSum() - requiredAmount;
                    this.setCreditLimit(this.getCreditLimit() + requiredAmount);
                    this.setMoneyAmount(getMoneyAmount() + remainingAmount);
                } else {
                    this.setCreditLimit(this.getCreditLimit() + cash.getSum());
                }
            } else {
                this.setMoneyAmount(this.getMoneyAmount() + cash.getSum());
            }
            atm.setLimit(atm.getLimit() + cash.getSum());
            log.info("putMoney return " + this.getMoneyAmount());
            return this.getMoneyAmount();
        }
    }
}
