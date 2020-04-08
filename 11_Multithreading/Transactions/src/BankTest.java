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
        Account account1 = new Account();

        account.setMoney(600_000);

        bank.setAccount(account);
        bank.setAccount(account1);

//        when(account.isBlock()).thenReturn(true);

        do {
            new Thread(() -> {
                bank.transfer(account.getAccNumber(), account1.getAccNumber(), 55_000);
            }).start();
            Thread.sleep(500);
        } while (!account.isBlock());
    }

    @Test
    public void testTransfer() {


        Account account = new Account();
        Account account1 = new Account();

        account.setMoney(200_000);
        account1.setMoney(200_000);

        bank.setAccount(account);
        bank.setAccount(account1);


        for (int i = 0; i < 20; i++) {

            new Thread(() -> {
                bank.transfer(account1.getAccNumber(), account.getAccNumber(), 10_000);
            }).start();
            new Thread(() -> {
                bank.transfer(account1.getAccNumber(), account.getAccNumber(), 5_000);
            }).start();
            new Thread(() -> {
                bank.transfer(account.getAccNumber(), account1.getAccNumber(), 15_000);
            }).start();
        }

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long money = bank.getBalance(account.getAccNumber());
        assertEquals(money, 200_000);
    }
}