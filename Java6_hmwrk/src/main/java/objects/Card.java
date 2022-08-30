package objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
abstract class Card {
    private final Currencies currency;
    private final Banks bank;
    private int moneyAmount;
    private final String cardNumber;
    private final String pinCode;

    public Card(Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount) {
        this.currency = currency;
        this.bank = bank;
        this.moneyAmount = moneyAmount;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }

    public abstract Cash withdrawMoney(ATM atm, int sum);
    public abstract int putMoney(ATM atm, Cash cash);
}
