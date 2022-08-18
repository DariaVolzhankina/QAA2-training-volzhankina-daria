
public class Application {
    public static void main(String[] args) {
        Card card = new Card("Sber", "1111222233334444", "1234", "rub", 10000);
        ATM atm = new ATM("Sber", "rub", 100000, Actions.PUT);
        Cash cash = new Cash(1000,"rub");

        boolean checkCard = atm.checkCard(card, "1234");

        Actions action = atm.getAction();

        if (checkCard && action.equals(Actions.WITHDRAW)) {
            atm.withdrawMoney(card, 100);
        } else if (checkCard && action.equals(Actions.PUT)) {
            atm.putMoney(card, cash);
        } else {
            System.out.println("Choose other ATM");
        }
    }
}
