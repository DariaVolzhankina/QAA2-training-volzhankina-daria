package objects;

import lombok.Data;

@Data
public class Card {
    private String currency;
    private String bank;
    private int moneyAmount;
    private String cardNumber;
    private String pinCode;

    public Card(String bank, String cardNumber, String pinCode, String currency, int moneyAmount) {
        this.currency = currency;
        this.bank = bank;
        this.moneyAmount = moneyAmount;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }
}
