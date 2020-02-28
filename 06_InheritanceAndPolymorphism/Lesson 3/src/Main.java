import Clients.Entrepreneur;
import Clients.LegalPerson;
import Clients.NaturalPerson;

public class Main {
    public static void main(String[] args) {

        NaturalPerson naturalPerson = new NaturalPerson();
        Entrepreneur entrepreneur = new Entrepreneur();
        LegalPerson legalPerson = new LegalPerson();


        entrepreneur.setMoney(9000);
        entrepreneur.getMoney(10000);
        System.out.println(entrepreneur.balance());
        System.out.println(entrepreneur.getPaymentAccount());

        naturalPerson.setMoney(-1000);
        naturalPerson.setMoney(100_000);
        System.out.println(naturalPerson.balance());
        System.out.println(naturalPerson.getPaymentAccount());

        legalPerson.setMoney(5000);
        legalPerson.getMoney(2312);
        System.out.println(legalPerson.balance());
        System.out.println(legalPerson.getPaymentAccount());

    }
}
