package Clients;

public abstract class Client {

    private double money;

    public abstract void withdrawMoney(double money);

    public abstract void putMoney(double money);

    public void setMoney(double money) {
        this.money = money;
    }

    public double balance() {
        return money;
    }
}
