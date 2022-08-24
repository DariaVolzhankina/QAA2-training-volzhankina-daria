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

    public Cash withdrawMoney(DebitCard card, int sum) {
        if (sum > limit) {
            return new Cash(0, this.currency);
        }
        if (sum > card.getMoneyAmount()) {
            return new Cash(0, this.currency);
        }

        if(sum < 0){
            return new Cash(0, this.currency);
        }

        card.setMoneyAmount(card.getMoneyAmount() - sum);
        limit -= sum;
        return new Cash(sum, this.currency);
    }

    public Cash withdrawMoney(CreditCard card, int sum) {
        if (sum > limit) {
            return new Cash(0, this.currency);
        }

        if (sum > (card.getMoneyAmount() + card.getCreditLimit())) {
            return new Cash(0, this.currency);
        }

        if(sum < 0){
            return new Cash(0, this.currency);
        }

        card.setCreditLimit(card.getMoneyAmount() + card.getCreditLimit() - sum);
        card.setMoneyAmount(0);
        limit -= sum;
        return new Cash(sum, this.currency);
    }


    public int putMoney(Card card, Cash cash) {
        if (cash.getSum() == 0) {
            return card.getMoneyAmount();
        }

        if (!currency.equals(cash.getCurrency())) {
            return card.getMoneyAmount();
        }

        if (cash.getSum() < 0) {
            return card.getMoneyAmount();
        }

        card.setMoneyAmount(card.getMoneyAmount() + cash.getSum());
        limit += cash.getSum();
        return card.getMoneyAmount();
    }
}

