package Clients;

public class NaturalPerson extends Client {

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
            this.money += money;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    @Override
    public double balance() {
        return money;
    }
}
