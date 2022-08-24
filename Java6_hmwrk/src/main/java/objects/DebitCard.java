package objects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DebitCard extends Card{
    public DebitCard(String bank, String cardNumber, String pinCode, String currency, int moneyAmount) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
    }
}
