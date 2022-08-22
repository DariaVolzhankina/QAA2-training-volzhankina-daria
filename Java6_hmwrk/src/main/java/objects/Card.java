package objects;

import lombok.Data;

@Data
public class Card {
    private String currency;
    private String bank;
    private int moneyAmount;
    private String cardNumber;
    private int pinCode;

    public Card(String bank, String cardNumber, int pinCode, String currency, int moneyAmount) {
        this.currency = currency;
        this.bank = bank;
        this.moneyAmount = moneyAmount;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }
}
