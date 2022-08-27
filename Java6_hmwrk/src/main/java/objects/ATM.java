package objects;

import exceptions.MoneyAmountException;
import exceptions.WrongBankException;
import exceptions.WrongCurrencyException;
import exceptions.WrongPinCodeException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ATM {
    private String currency;
    private String bank;
    private int limit;

    public ATM(String bank, String currency, int limit) {
        this.currency = currency;
        this.bank = bank;
        this.limit = limit;
    }

    public boolean checkBank(Card card) throws WrongBankException {
        if (bank == null || card.getBank() == null) {
            throw new WrongBankException("Bank value cannot be null");
        }

        if (bank.trim().equals("") || card.getBank().trim().equals("")) {
            throw new WrongBankException("Bank value cannot be empty");
        }

        if (!bank.equals(card.getBank())) {
            throw new WrongBankException("Card of another bank");
        }
        return bank.equals(card.getBank());
    }

    public boolean checkPinCode(Card card, String pin) {
        if (pin == null || card.getPinCode() == null) {
            throw new WrongPinCodeException("pin code cannot be null");
        }

        if (pin.trim().equals("") || card.getPinCode().trim().equals("")) {
            throw new WrongPinCodeException("pin code cannot be empty");
        }

        if (!card.getPinCode().equals(pin.trim())) {
            throw new WrongPinCodeException("invalid pin code");
        }

        return card.getPinCode().equals(pin.trim());
    }

    public boolean checkCurrency(Card card) throws WrongCurrencyException {
        if (currency == null || card.getCurrency() == null) {
            throw new WrongCurrencyException("Currency cannot be null");
        }

        if (currency.trim().equals("") || card.getCurrency().trim().equals("")) {
            throw new WrongCurrencyException("Currency cannot be empty");
        }

        if (!currency.equals(card.getCurrency())) {
            throw new WrongCurrencyException("The ATM issues another currency");
        }

        return currency.equals(card.getCurrency());
    }

    public Cash withdrawMoney(DebitCard card, int sum) throws MoneyAmountException, WrongCurrencyException {

        checkCurrency(card);

        if (sum > limit) {
            throw new MoneyAmountException("The ATM issues an amount up to" + this.getLimit());
        } else if (sum > card.getMoneyAmount()) {
            throw new MoneyAmountException("not enough money on the card");
        } else if (sum < 0 || this.getLimit() < 0 || card.getMoneyAmount() < 0) {
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (sum == 0) {
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (this.getLimit() == 0) {
            throw new MoneyAmountException("ATM have no money");
        } else if (card.getMoneyAmount() == 0) {
            throw new MoneyAmountException("You have no money");
        } else {
            card.setMoneyAmount(card.getMoneyAmount() - sum);
            limit -= sum;
            return new Cash(sum, this.currency);
        }
    }

    public Cash withdrawMoney(CreditCard card, int sum) throws MoneyAmountException, WrongCurrencyException {

        checkCurrency(card);

        if (sum > limit) {
            throw new MoneyAmountException("The ATM issues an amount up to" + this.getLimit());
        }  else if (sum < 0 || this.getLimit() < 0 || card.getMoneyAmount() < 0 || card.getCreditLimit() < 0) {
            throw new MoneyAmountException("the amount cannot be less than zero");
        } else if (sum == 0) {
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (this.getLimit() == 0) {
            throw new MoneyAmountException("ATM have no money");
        } else if (card.getMoneyAmount() + card.getCreditLimit() == 0) {
            throw new MoneyAmountException("You have no money");
        } else if (sum > (card.getMoneyAmount() + card.getCreditLimit())) {
            throw new MoneyAmountException("not enough money on the card");
        } else {
            if (sum > card.getMoneyAmount()) {
                card.setCreditLimit(card.getMoneyAmount() + card.getCreditLimit() - sum);
                card.setMoneyAmount(0);
            }else{
                card.setMoneyAmount(card.getMoneyAmount() - sum);
            }
            limit -= sum;
            return new Cash(sum, this.currency);
        }
    }


    public int putMoney(Card card, Cash cash) throws MoneyAmountException, WrongCurrencyException {

        checkCurrency(card);

        if (cash.getCurrency() == null) {
            throw new WrongCurrencyException("Currency cannot be null");
        } else if (cash.getSum() == 0) {
            throw new MoneyAmountException("the sum cannot be zero");
        } else if (cash.getSum() < 0) {
            throw new MoneyAmountException("the amount cannot be less than zero");
        }else if(currency.trim().equals("") || cash.getCurrency().trim().equals("")){
            throw new WrongCurrencyException("Currency cannot be empty");
        } else if (!currency.equals(cash.getCurrency())) {
            throw new WrongCurrencyException("The objects.ATM only issues " + this.getCurrency());
        } else {
            card.setMoneyAmount(card.getMoneyAmount() + cash.getSum());
            limit += cash.getSum();
            return card.getMoneyAmount();
        }
    }
}

