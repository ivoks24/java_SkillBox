import java.util.concurrent.atomic.AtomicLong;

public class Account
{
    private AtomicLong money = new AtomicLong();
    private String accNumber;
    private static int accountCount = 1;
    private boolean block = false;

    public Account() {

        money.set(0); //(int) (Math.random() * 90_000 + 100_000));

        accNumber = String.format("%06d", accountCount);
        accountCount++;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public void getMoney(long money) {
        this.money.set(this.money.get() - money);
    }

    public void setMoney(long money) {
        this.money.set(this.money.get() + money);
    }

    public long getBalance() {
        return money.get();
    }

    public String getAccNumber() {
        return accNumber;
    }
}
