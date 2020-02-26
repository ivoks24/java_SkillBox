package Clients;

public class LegalPerson extends Client {

    public void getMoney(double money) {

        double commission = money * 0.01;
        super.getMoney(money + commission);

    }
}
