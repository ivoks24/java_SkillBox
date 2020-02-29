package Clients;

public abstract class Client {

    private int paymentAccount;
    private double money;

    public Client() {

        paymentAccount = 1_000_000 + (int) (Math.random() * 9_000_000);
    }

    public String getPaymentAccount() {
        return "Your account: " + paymentAccount;
    }

    public abstract void withdrawMoney(double money);

    public abstract void putMoney(double money);

    public void setMoney(double money) {
        this.money = money;
    }

    public double balance() {
        return money;
    }
}
