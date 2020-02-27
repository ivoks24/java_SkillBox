package Banking;

public class CardAccount extends Account {

@Override
    public void getMoney(double money) {

        super.getMoney(money * 1.01);
    }
}
