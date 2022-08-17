public class Application {
    public static void main(String[] args) {
        Card card = new Card("Tinkoff", "1111222233334444", "1234", "rub", 10000);
        ATM atm = new ATM("Tinkoff", "rub", 100000);

        boolean checkCard = atm.checkCard(card, "1234");

        String withdraw = "withdraw";
        String put = "put";

        String action = atm.selectAction(withdraw);

        if (checkCard && action.equals(withdraw)) {
            atm.withdrawMoney(card, 100);
        } else if (checkCard && action.equals(put)) {

        } else {
            System.out.println("Вы не выбрали действие!");
        }
    }
}
