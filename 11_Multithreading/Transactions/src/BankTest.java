import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class BankTest extends TestCase {

    private Bank bank;

    @Before
    public void setUp() {

        bank = new Bank();

    }

    @Test
    public void testTransfer_siBlock() throws InterruptedException {

        Account account = new Account();
//        Account account = mock(Account.class);
        Account account1 = new Account();

        account.setMoney(600_000);

        bank.setAccount(account);
        bank.setAccount(account1);

//        when(account.isBlock()).thenReturn(true);
        do {
            bank.transfer(account.getAccNumber(), account1.getAccNumber(), 55_000);
            Thread.sleep(500);
        } while (!account.isBlock());
    }

    @Test
    public void testTransfer() {

        Account account = new Account();
        Account account1 = new Account();
        Account account2 = new Account();

        account1.setMoney(100_000);
        account2.setMoney(100_000);

        bank.setAccount(account);
        bank.setAccount(account1);
        bank.setAccount(account2);

        bank.transfer(account1.getAccNumber(), account.getAccNumber(), 10_000);
        bank.transfer(account2.getAccNumber(), account.getAccNumber(), 10_000);
        bank.transfer(account1.getAccNumber(), account.getAccNumber(), 25_000);
        bank.transfer(account.getAccNumber(), account1.getAccNumber(), 5_000);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long money = bank.getBalance(account.getAccNumber());
        assertEquals(money, 40_000);
    }
}