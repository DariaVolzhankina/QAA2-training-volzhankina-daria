package objects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreditCard extends Card {
    private int creditLimit;

    public CreditCard(String bank, String cardNumber, String pinCode, String currency, int moneyAmount, int creditLimit) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
        this.creditLimit = creditLimit;
    }
}
