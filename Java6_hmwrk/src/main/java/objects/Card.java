package objects;

import lombok.Data;

@Data
public class Card {
    private String currency;
    private String bank;
    private int moneyAmount;
    private String cardNumber;
    private String pinCode;
    private int creditLimit;

    public Card(String bank, String cardNumber, String pinCode, String currency, int moneyAmount) {
        this.currency = currency;
        this.bank = bank;
        this.moneyAmount = moneyAmount;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }

    public Cash withdrawMoney(ATM atm, int sum) {
        return null;
    }

    public int putMoney(ATM atm, Cash cash) {
        if (!atm.getCurrency().equals(cash.getCurrency())) {
            System.out.println("The objects.ATM only issues " + atm.getCurrency());
            return this.getMoneyAmount();
        } else if (cash.getSum() <= 0) {
            System.out.println("the sum cannot be equal to or less than zero");
            return this.getMoneyAmount();
        }
        this.setMoneyAmount(this.getMoneyAmount() + cash.getSum());
        atm.setLimit(atm.getLimit() + cash.getSum());
        System.out.println(this.getMoneyAmount());
        return this.getMoneyAmount();
    }
}
