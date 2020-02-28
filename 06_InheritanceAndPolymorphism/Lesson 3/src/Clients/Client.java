package Clients;

public abstract class Client {

    private int paymentAccount;

    public Client() {

        paymentAccount = 1_000_000 + (int) (Math.random() * 9_000_000);
    }

    public String getPaymentAccount() {
        return "Your account: " + paymentAccount;
    }

    public abstract void getMoney(double money);

    public abstract void setMoney(double money);

    public abstract double balance();
}
