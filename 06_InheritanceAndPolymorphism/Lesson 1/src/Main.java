import Banking.Account;
import Banking.CardAccount;
import Banking.DepositoryCurrentAccount;

public class Main {
    public static void main(String[] args) {

        CardAccount cardAccount = new CardAccount();
        DepositoryCurrentAccount depositoryCurrentAccount = new DepositoryCurrentAccount();

        //depositoryCurrentAccount.putMoney(10_000);
        depositoryCurrentAccount.getMoney(9000);
        System.out.println(depositoryCurrentAccount.balance());

        cardAccount.putMoney(9000);
        cardAccount.getMoney(8990);
        System.out.println(cardAccount.balance());
    }
}
