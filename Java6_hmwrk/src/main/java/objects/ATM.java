package objects;

import enums.Actions;
import exceptions.*;
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

    public boolean checkBank(Card card) {
        return bank.equals(card.getBank());
    }

    public boolean checkPinCode(Card card, int pin) {
        return card.getPinCode() == pin;
    }

    public boolean checkCurrency(Card card) {
        return currency.equals(card.getCurrency());
    }

    public boolean checkCard(Card card, int pin) {
        boolean checkPinCode = checkPinCode(card, pin);
        boolean checkCurrency = checkCurrency(card);

        if (checkPinCode && checkCurrency) {
            return true;
        }

        try {
            if (!checkPinCode) {
                System.out.println("invalid pin code");
                throw new WrongPinCodeException("invalid pin code");
            }
        } catch (WrongPinCodeException e) {
            e.printStackTrace();
            return false;
        }

        try {
            System.out.println("The objects.ATM only issues " + currency);
            throw new WrongCurrencyException("The objects.ATM only issues " + currency);
        } catch (WrongCurrencyException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cash withdrawMoney(Card card, int sum) {

        try {
            if (sum == 0) {
                System.out.println("enter the amount of money");
                throw new MoneyAmountException("no amount of money entered");
            }
        } catch (MoneyAmountException e) {
            e.printStackTrace();
            return new Cash(0, this.currency);
        }

        try {
            if (sum > limit) {
                System.out.println("The objects.ATM issues an amount up to " + limit);
                throw new MoneyAmountException("objects.ATM limit exceeded");
            }
        } catch (MoneyAmountException e) {
            e.printStackTrace();
            return new Cash(0, this.currency);
        }

        try {
            if (sum > card.getMoneyAmount()) {
                System.out.println(("not enough money on the card"));
                throw new MoneyAmountException("not enough money on the card");
            }
        } catch (MoneyAmountException e) {
            e.printStackTrace();
            return new Cash(0, this.currency);
        }

        card.setMoneyAmount(card.getMoneyAmount() - sum);
        limit -= sum;
        System.out.println(card.getMoneyAmount());
        return new Cash(sum, this.currency);
    }


    public int putMoney(Card card, Cash cash) {
        try {
            if (cash.getSum() == 0) {
                System.out.println("enter the amount of money");
                throw new MoneyAmountException("no amount of money entered");
            }
        } catch (MoneyAmountException e) {
            e.printStackTrace();
            return card.getMoneyAmount();
        }

        try {
            if (!currency.equals(cash.getCurrency())) {
                System.out.println("The objects.ATM only issues " + currency);
                throw new WrongCurrencyException("The objects.ATM only issues " + currency);
            }
        } catch (WrongCurrencyException e) {
            e.printStackTrace();
            return card.getMoneyAmount();
        }

        card.setMoneyAmount(card.getMoneyAmount() + cash.getSum());
        limit += cash.getSum();
        return card.getMoneyAmount();
    }
}

