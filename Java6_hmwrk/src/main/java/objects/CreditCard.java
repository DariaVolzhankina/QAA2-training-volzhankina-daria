package objects;

public class CreditCard extends Card {
    private int creditLimit;

    @Override
    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public int getCreditLimit() {
        return creditLimit;
    }

    public CreditCard(String bank, String cardNumber, String pinCode, String currency, int moneyAmount, int creditLimit) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
        this.creditLimit = creditLimit;
    }
}
