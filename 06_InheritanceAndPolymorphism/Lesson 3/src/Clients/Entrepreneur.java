package Clients;

public class Entrepreneur extends Client {

    private double money;

    @Override
    public void getMoney(double money) {

        if (this.money >= money && money > 0) {
            this.money -= money;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    @Override
    public void setMoney(double money) {

        if (money > 0) {
            if (money < 1000) {
                this.money += money * 0.99;
            } else {
                this.money += money * 0.995;
            }
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    @Override
    public double balance() {
        return money;
    }
}
