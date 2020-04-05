public class Account
{
    private long money;
    private String accNumber;
    private static int accountCount = 1;
    private boolean block = false;

    public Account() {

        money = (int) (Math.random() * 90_000 + 1000_000);

        accNumber = String.format("%06d", accountCount);
        accountCount++;
        System.out.println(accNumber);
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public void getMoney(long money) {
        this.money -= money;
    }

    public void setMoney(long money) {
        this.money += money;
    }

    public long getBalance() {
        return money;
    }

    public String getAccNumber() {
        return accNumber;
    }
}
