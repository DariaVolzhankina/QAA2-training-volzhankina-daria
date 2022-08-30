package objects;

import exceptions.MoneyAmountException;
import exceptions.WrongCurrencyException;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

@Slf4j
public class DebitCard extends Card {

    public DebitCard(Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
    }

    @Override
    public Cash withdrawMoney(ATM atm, int sum) throws MoneyAmountException {

        if (sum > atm.getLimit()) {
            log.warn("The ATM issues an amount up to" + atm.getLimit());
            throw new MoneyAmountException("The ATM issues an amount up to" + atm.getLimit());
        } else if (sum > this.getMoneyAmount()) {
            log.warn("not enough money on the card");
            throw new MoneyAmountException("not enough money on the card");
        } else if (sum <= 0 || atm.getLimit() <= 0 || this.getMoneyAmount() <= 0) {
            log.warn("the values cannot be equal to zero or less than zero");
            throw new MoneyAmountException("the values cannot be equal to zero or less than zero");
        } else {
            this.setMoneyAmount(this.getMoneyAmount() - sum);
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
            this.setMoneyAmount(this.getMoneyAmount() + cash.getSum());
            atm.setLimit(atm.getLimit() + cash.getSum());
            log.info("putMoney return " + this.getMoneyAmount());
            return this.getMoneyAmount();
        }
    }
}
