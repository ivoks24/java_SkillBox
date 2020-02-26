package Banking;

public class Account {

    private double money;

    public void getMoney(double money) {

        if (this.money >= money && money > 0) {
            this.money -= money;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void putMoney(double money) {
        this.money += money;
    }

    public double balance() {
        return money;
    }

}
