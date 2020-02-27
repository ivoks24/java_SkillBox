package Clients;

public class Entrepreneur extends Client {

@Override
    public void setMoney(double money) {

        double commission = money * 0.005;

        if (money < 1000) {
            super.setMoney(money - commission);
        } else {
            super.setMoney(money - commission * 2);
        }

    }
}
