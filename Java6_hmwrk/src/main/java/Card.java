public class Card {
    private String currency;
    private String bank;

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    private int moneyAmount;
    private String cardNumber;
    private String pinCode;

    public String getCurrency() {
        return currency;
    }

    public String getBank() {
        return bank;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public Card(String bank, String cardNumber, String pinCode, String currency, int moneyAmount) {
        this.currency = currency;
        this.bank = bank;
        this.moneyAmount = moneyAmount;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }
}
