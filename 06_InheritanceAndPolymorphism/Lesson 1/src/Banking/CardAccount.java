package Banking;

public class CardAccount extends Account {

    public void getMoney(double money) {

        double commission = money * 0.01;
        double remains = balance() - commission - money;

        if (remains >= 0) {
            super.getMoney(money + commission);
        } else {
            System.out.println("Operation is not possible!");
        }
    }
}
