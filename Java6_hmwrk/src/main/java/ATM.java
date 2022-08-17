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

    public boolean checkCard(Card card, String pin){
        boolean checkBank = checkBank(card);
        boolean checkPinCode = checkPinCode(card, pin);
        boolean checkCurrency = checkCurrency(card);

        if(checkBank && checkPinCode && checkCurrency){
            return true;
        } else if(!checkBank){
            System.out.println("your card is not a " + bank + " bank card. Insert a " + bank + " bank card");
        } else if(!checkPinCode){
            System.out.println("invalid pin code");
        }else {
            System.out.println("The ATM only issues " + currency);
        }
        return false;
    }

    public Cash withdrawMoney(Card card, int sum) {
        int limit = this.limit;
        if(sum <= limit && sum <= card.getMoneyAmount()){
            card.setMoneyAmount(card.getMoneyAmount() - sum);
            System.out.println(card.getMoneyAmount());
            return new Cash(sum,this.currency);
        }else if(sum > limit){
            System.out.println("the ATM gives out no more than " + limit + this.currency);
        }else if (sum > card.getMoneyAmount()){
            System.out.println("there are not enough funds on your card");
        }
        return new Cash(0,this.currency);
    }


    public void putMoney(Card card, Cash cash){
        if(currency.equals(cash.getCurrency()) && card.getCurrency().equals(cash.getCurrency())){
            card.setMoneyAmount(card.getMoneyAmount() + cash.getSum());
            this.limit += cash.getSum();
            System.out.println(limit);
        }else {
            System.out.println("Put money in another currency");
        }
    }

    public String selectAction(String action){
        return action;
    }
}
