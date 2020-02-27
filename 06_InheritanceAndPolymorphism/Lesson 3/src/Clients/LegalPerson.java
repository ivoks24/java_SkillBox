package Clients;

public class LegalPerson extends Client {

@Override
    public void getMoney(double money) {

        double commission = money * 0.01;
        super.getMoney(money + commission);

    }
}
