public class Application {
    public static void main(String[] args) {
        Card card = new Card("Tinkoff", "1111222233334444", "1234", "rub", 1000);
        ATM atm = new ATM("Tinkoff","rub",100000);

        boolean checkBank = atm.checkBank(card);
        if(checkBank){
            boolean checkPin = atm.checkPinCode(card, "1234");
            if (checkPin){

            }
        }
    }
}
