package objects;

public class DebitCard extends Card {
    public DebitCard(String bank, String cardNumber, String pinCode, String currency, int moneyAmount) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
    }

    @Override
    public int getCreditLimit() {
        return 0;
    }
}
