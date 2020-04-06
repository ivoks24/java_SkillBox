import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankTest extends TestCase {

    private Bank bank;
    List<String> accNumber;

    @Before
    public void setUp() {

        bank = new Bank();
        HashMap<String, Account> accounts= new HashMap<>();

        for (int i = 0; i < 3; i++) {
            Account account = new Account();
            account.setMoney(60_000);
            accounts.put(account.getAccNumber(), account);
        }

        accNumber = new ArrayList<>(accounts.keySet());
        bank.setAccounts(accounts);
    }

    @Test
    public void testTransfer() throws InterruptedException {

        bank.transfer(accNumber.get(0), accNumber.get(1), 40_000);
        bank.transfer(accNumber.get(2), accNumber.get(0), 35_000);

        Thread.sleep(100);

        assertEquals(bank.getBalance(accNumber.get(0)), 55_000);
    }


}