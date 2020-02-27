import Banking.Account;
import Banking.CardAccount;
import Banking.DepositoryCurrentAccount;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {

        CardAccount cardAccount = new CardAccount();
        DepositoryCurrentAccount depositoryCurrentAccount = new DepositoryCurrentAccount();


        //depositoryCurrentAccount.putMoney(10_000);
        depositoryCurrentAccount.getMoney(9000);
        System.out.println(depositoryCurrentAccount.getOpenDate());
        System.out.println(depositoryCurrentAccount.balance());

        cardAccount.putMoney(9000);
        cardAccount.getMoney(8990);
        System.out.println(cardAccount.balance());
    }
}
