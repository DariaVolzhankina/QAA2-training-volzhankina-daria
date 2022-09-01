package objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import objects.enums.Banks;
import objects.enums.Currencies;

import java.util.Objects;

@Data
@AllArgsConstructor
public abstract class Card {
    private final Currencies currency;
    private final Banks bank;
    private final String cardNumber;
    private final String pinCode;
    private int moneyAmount;

    public Card(Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount) {
        this.currency = Objects.requireNonNull(currency);
        this.bank = Objects.requireNonNull(bank);
        this.moneyAmount = moneyAmount;
        this.cardNumber = Objects.requireNonNull(cardNumber);
        this.pinCode = Objects.requireNonNull(pinCode);
    }

    public abstract Cash withdrawMoney(ATM atm, int sum);
    public abstract int putMoney(ATM atm, Cash cash);
    public abstract int checkMoneyAmount();
}
