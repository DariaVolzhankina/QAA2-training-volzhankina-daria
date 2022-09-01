package objects;

import exceptions.*;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

import java.util.Scanner;
import java.util.regex.Pattern;

@Data
@Slf4j
public class ATM {
    private final Currencies currency;
    private final Banks bank;
    private int limit;


    public ATM(@NonNull Banks bank, @NonNull Currencies currency, int limit) {
        this.currency = currency;
        this.bank = bank;
        this.limit = limit;
    }

    public boolean checkBank(Card card) throws WrongBankException {

        if (!bank.equals(card.getBank())) {
            log.warn("Card of another bank");
            throw new WrongBankException("Card of another bank");
        }

        log.info("check bank was successful");
        return bank.equals(card.getBank());
    }

    public boolean checkPinCode(Card card, String pin) {
        String regex = "^\\d{4}$";

        if (pin == null) {
            log.warn("pin code cannot be null");
        }

        if(!Pattern.matches(regex, pin) || !card.getPinCode().equals(pin)){
            throw new WrongPinCodeException("invalid pin code");
        }

        log.info("check pin code was successful");
        return card.getPinCode().equals(pin);
    }

    public boolean checkCurrency(Card card) throws WrongCurrencyException {
        if (currency.equals(card.getCurrency())) {
            log.info("check currency was successful");
            return currency.equals(card.getCurrency());
        }else{
            log.warn("The ATM issues another currency");
            throw new WrongCurrencyException("The ATM issues another currency");
        }
    }


    public boolean chooseAction(Scanner s, Card card, Cash cash, boolean b1) {
        // 1 - withdraw, 2 - put, 3 - check money amount, 4 - exit, 5 - check credit limit
        int choice = s.nextInt();
        switch (choice) {
            case 1:
                int sum = s.nextInt();
                try {
                    card.withdrawMoney(this, sum);
                } catch (MoneyAmountException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    card.putMoney(this, cash);
                } catch (MoneyAmountException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                card.checkMoneyAmount();
                break;
            case 4:
                b1 = false;
                break;
            case 5:
                if(card instanceof CreditCard){
                    ((CreditCard) card).checkCreditLimit();
                    break;
                }else{
                    throw new WrongActionException("you entered the wrong number");
                }
            default:
                throw new WrongActionException("you entered the wrong number");
        }
        return b1;
    }
}

