package Clients;

public class NaturalPerson extends Client {

    @Override
    public void withdrawMoney(double money) {

        if (balance() >= money && money > 0) {
            setMoney(balance() - money);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    @Override
    public void putMoney(double money) {

        if (money > 0) {
            setMoney(balance() + money);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}
