import Clients.Entrepreneur;
import Clients.LegalPerson;
import Clients.NaturalPerson;

public class Main {
    public static void main(String[] args) {

        NaturalPerson naturalPerson = new NaturalPerson();
        Entrepreneur entrepreneur = new Entrepreneur();
        LegalPerson legalPerson = new LegalPerson();


        entrepreneur.putMoney(9000);
        entrepreneur.withdrawMoney(10000);
        System.out.println(entrepreneur.balance());
        System.out.println(entrepreneur.getPaymentAccount());

        naturalPerson.putMoney(-1000);
        naturalPerson.putMoney(100_000);
        System.out.println(naturalPerson.balance());
        System.out.println(naturalPerson.getPaymentAccount());

        legalPerson.putMoney(5000);
        legalPerson.withdrawMoney(2312);
        System.out.println(legalPerson.balance());
        System.out.println(legalPerson.getPaymentAccount());

    }
}
