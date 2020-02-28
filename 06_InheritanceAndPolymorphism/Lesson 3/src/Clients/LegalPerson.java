package Clients;

public class LegalPerson extends Client {

    private double money;

@Override
    public void getMoney(double money) {

    money = money * 1.01;
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
