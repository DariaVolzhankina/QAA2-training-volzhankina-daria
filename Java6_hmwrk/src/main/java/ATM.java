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

    public boolean checkCurrency(Card card) {
        String currency = this.currency;
        return currency.equals(card.getCurrency());
    }

    public Cash withdrawMoney(Card card, Cash cash) {
        int sum = cash.getSum();
        int limit = this.limit;
        if(sum <= limit && sum <= card.getMoneyAmount()){
            return cash;
        }else if(sum > limit){
            System.out.println("the ATM gives out no more than " + limit + this.currency);
        }else if (sum > card.getMoneyAmount()){
            System.out.println("there are not enough funds on your card");
        }
        return new Cash(0,this.currency);
    }
}

