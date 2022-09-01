package objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
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

    public Card(@NonNull Banks bank, @NonNull String cardNumber, @NonNull String pinCode, @NonNull Currencies currency, int moneyAmount) {
        this.currency = currency;
        this.bank = bank;
        this.moneyAmount = moneyAmount;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }

    public abstract Cash withdrawMoney(ATM atm, int sum);
    public abstract int putMoney(ATM atm, Cash cash);
    public abstract int checkMoneyAmount();
}
