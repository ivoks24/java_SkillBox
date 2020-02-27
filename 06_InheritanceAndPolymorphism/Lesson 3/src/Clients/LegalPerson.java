package Clients;

public class LegalPerson extends Client {

@Override
    public void getMoney(double money) {

        super.getMoney(money * 1.01);

    }
}
