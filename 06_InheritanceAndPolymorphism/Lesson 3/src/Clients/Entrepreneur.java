package Clients;

public class Entrepreneur extends Client {

@Override
    public void setMoney(double money) {

        if (money < 1000) {
            super.setMoney(money * 0.99);
        } else {
            super.setMoney(money * 0.995);
        }

    }
}
