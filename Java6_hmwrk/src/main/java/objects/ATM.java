package objects;

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
}

