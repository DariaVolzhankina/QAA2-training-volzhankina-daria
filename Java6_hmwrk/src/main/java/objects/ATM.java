package objects;

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

    public boolean checkPinCode(Card card, String pin) {
        return card.getPinCode().equals(pin);
    }

    public boolean checkCurrency(Card card) {
        return currency.equals(card.getCurrency());
    }

    public Cash withdrawMoney(Card card, int sum) {
        if (sum > limit) {
            return new Cash(0, this.currency);
        } else if (sum > card.getMoneyAmount()) {
            return new Cash(0, this.currency);
        } else {
            card.setMoneyAmount(card.getMoneyAmount() - sum);
            limit -= sum;
            System.out.println(card.getMoneyAmount());
            return new Cash(sum, this.currency);
        }
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
        System.out.println(card.getMoneyAmount());
        return card.getMoneyAmount();
    }
}

