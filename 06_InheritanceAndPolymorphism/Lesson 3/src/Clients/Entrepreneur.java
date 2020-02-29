package Clients;

public class Entrepreneur extends Client {

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
            if (money < 1000) {
                setMoney(balance() + money * 0.99);
            } else {
                setMoney(balance() + money * 0.995);
            }
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}
