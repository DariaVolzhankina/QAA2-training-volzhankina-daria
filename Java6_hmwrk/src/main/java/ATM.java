import exceptions.MoneyAmountException;
import exceptions.WrongBankException;
import exceptions.WrongCurrencyException;
import exceptions.WrongPinCodeException;
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

    public boolean checkCard(Card card, String pin) {
        boolean checkBank = checkBank(card);
        boolean checkPinCode = checkPinCode(card, pin);
        boolean checkCurrency = checkCurrency(card);

        if (checkBank && checkPinCode && checkCurrency) {
            return true;
        }

        try {
            if (!checkBank) {
                System.out.println("your card is not a " + bank + " bank card. Insert a " + bank + " bank card");
                throw new WrongBankException("ATM accepts only " + bank + " cards");
            }
        } catch (WrongBankException e) {
            e.printStackTrace();
            return false;
        }

        try {
            if (!checkPinCode) {
                System.out.println("invalid pin code");
                throw new WrongPinCodeException("invalid pin code");
            }
        } catch (WrongPinCodeException e) {
            e.printStackTrace();
            return false;
        }

        try {
            System.out.println("The ATM only issues " + currency);
            throw new WrongCurrencyException("The ATM only issues " + currency);
        } catch (WrongCurrencyException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cash withdrawMoney(Card card, int sum) {

        try {
            if (sum == 0) {
                System.out.println("enter the amount of money");
                throw new MoneyAmountException("no amount of money entered");
            }
        } catch (MoneyAmountException e) {
            e.printStackTrace();
            return new Cash(0, this.currency);
        }

        try {
            if (sum > limit) {
                System.out.println("The ATM issues an amount up to " + limit);
                throw new MoneyAmountException("ATM limit exceeded");
            }
        } catch (MoneyAmountException e) {
            e.printStackTrace();
            return new Cash(0, this.currency);
        }

        try {
            if (sum > card.getMoneyAmount()) {
                System.out.println(("not enough money on the card"));
                throw new MoneyAmountException("not enough money on the card");
            }
        } catch (MoneyAmountException e) {
            e.printStackTrace();
            return new Cash(0, this.currency);
        }

        card.setMoneyAmount(card.getMoneyAmount() - sum);
        limit -= sum;
        return new Cash(sum, this.currency);
    }


    public int putMoney(Card card, Cash cash) {
        try {
            if (cash.getSum() == 0) {
                System.out.println("enter the amount of money");
                throw new MoneyAmountException("no amount of money entered");
            }
        } catch (MoneyAmountException e) {
            e.printStackTrace();
            return card.getMoneyAmount();
        }

        try {
            if (!currency.equals(cash.getCurrency())) {
                System.out.println("The ATM only issues " + currency);
                throw new WrongCurrencyException("The ATM only issues " + currency);
            }
        } catch (WrongCurrencyException e) {
            e.printStackTrace();
            return card.getMoneyAmount();
        }

        card.setMoneyAmount(card.getMoneyAmount() + cash.getSum());
        limit += cash.getSum();
        return card.getMoneyAmount();
    }

    public String selectAction(String action) {
        return action;
    }
}

