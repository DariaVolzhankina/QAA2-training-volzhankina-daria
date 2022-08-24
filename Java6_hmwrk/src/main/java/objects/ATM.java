package objects;

import exceptions.WrongBankException;
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

    public boolean checkBank(Card card) throws WrongBankException {
        if (bank == null || card.getBank() == null){
            return false;
        }

        if(bank.trim().equals("") || card.getBank().trim().equals("")){
            return false;
        }

        return bank.equals(card.getBank());
    }

    public boolean checkPinCode(Card card, String pin) {
        if (pin == null || card.getPinCode() == null){
            return false;
        }

        if(pin.trim().equals("") || card.getPinCode().trim().equals("")){
            return false;
        }

        return card.getPinCode().equals(pin.trim());
    }

    public boolean checkCurrency(Card card) {
        if (currency == null || card.getCurrency() == null){
            return false;
        }

        if(currency.trim().equals("") || card.getCurrency().trim().equals("")){
            return false;
        }

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

