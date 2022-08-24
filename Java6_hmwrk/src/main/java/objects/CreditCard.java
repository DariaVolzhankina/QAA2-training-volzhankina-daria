package objects;

public class CreditCard extends Card {
    private int creditLimit2;

    @Override
    public int getCreditLimit() {
        return creditLimit2;
    }

    public CreditCard(String bank, String cardNumber, String pinCode, String currency, int moneyAmount, int creditLimit) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
        this.creditLimit2 = creditLimit;
    }

    @Override
    public Cash withdrawMoney(ATM atm, int sum) {
        if (sum > atm.getLimit()) {
            return new Cash(0, this.getCurrency());
        } else if (sum > (this.getMoneyAmount() + this.getCreditLimit())) {
            return new Cash(0, this.getCurrency());
        } else {
            this.setMoneyAmount(this.getMoneyAmount() + this.getCreditLimit() - sum);
            atm.setLimit(atm.getLimit() - sum);
            System.out.println(this.getMoneyAmount());
            return new Cash(sum, this.getCurrency());
        }
    }
}
