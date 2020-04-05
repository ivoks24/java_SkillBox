import java.util.HashMap;
import java.util.Random;

public class Bank {

    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {

        Thread thread = new Thread(()-> {

            if (fromAccountNum.equals(toAccountNum)) {
                return;
            }

            Account fromAccount = getAccount(fromAccountNum);
            Account toAccount = getAccount(toAccountNum);

            synchronized (fromAccount) {
                synchronized (toAccount) {
                    if (!fromAccount.isBlock() && !toAccount.isBlock()
                            && (fromAccount.getBalance() >= amount && amount > 0)) {

                        fromAccount.getMoney(amount);
                        toAccount.setMoney(amount);
                        System.out.println("Операция прошла успещно!");
                        boolean isBlock = false;

                        if (amount > 50_000) {
                            try {
                                isBlock = isFraud(fromAccountNum, toAccountNum, amount);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        fromAccount.setBlock(isBlock);
                        toAccount.setBlock(isBlock);

                    } else {
                        System.err.println("Один или оба аккаунта заблокированы!" +
                                "\n Либо проверьте наличие переводимой суммы.");
                    }
                }
            }

        });

        thread.start();

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {

        Account account = getAccount(accountNum);
        synchronized (account) {
            return getAccount(accountNum).getBalance();
        }
    }

    private Account getAccount(String accountNum) {
        return accounts.getOrDefault(accountNum, null);
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }
}
