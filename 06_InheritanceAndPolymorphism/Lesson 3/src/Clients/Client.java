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

    public void getMoney(double money) {

        if (this.money >= money && money > 0) {
            this.money -= money;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void setMoney(double money) {

        if (money > 0) {
            this.money += money;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public double balance() {
        return money;
    }
}
