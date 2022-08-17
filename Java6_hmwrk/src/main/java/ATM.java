public class ATM {
    private String currency;
    private String bank;
    private int limit;

    public String getCurrency() {
        return currency;
    }

    public String getBank() {
        return bank;
    }

    public int getLimit() {
        return limit;
    }

    public ATM(String bank, String currency, int limit) {
        this.currency = currency;
        this.bank = bank;
        this.limit = limit;
    }

    public boolean checkBank(Card card) {
        String bank = this.bank;
        if (bank.equals(card.getBank())) {
            System.out.println("Enter pin code");
            return true;
        } else {
            System.out.println("your card is not a " + bank + " bank card. Insert a " + bank + " bank card");
            return false;
        }
    }

    public boolean checkPinCode(Card card, String pin) {
        if (card.getPinCode().equals(pin)) {
            System.out.println("select an action");
            return true;
        } else {
            System.out.println("invalid pin code");
            return false;
        }
    }
}
