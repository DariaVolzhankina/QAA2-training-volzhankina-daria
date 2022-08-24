package objects;

public class DebitCard extends Card {
    public DebitCard(String bank, String cardNumber, String pinCode, String currency, int moneyAmount) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
    }

    @Override
    public int getCreditLimit() {
        return 0;
    }

    @Override
    public Cash withdrawMoney(ATM atm, int sum) {
        if (sum > atm.getLimit()) {
            return new Cash(0, this.getCurrency());
        } else if (sum > this.getMoneyAmount()) {
            return new Cash(0, this.getCurrency());
        } else {
            this.setMoneyAmount(this.getMoneyAmount() - sum);
            atm.setLimit(atm.getLimit() - sum);
            System.out.println(this.getMoneyAmount());
            return new Cash(sum, this.getCurrency());
        }
    }
}
