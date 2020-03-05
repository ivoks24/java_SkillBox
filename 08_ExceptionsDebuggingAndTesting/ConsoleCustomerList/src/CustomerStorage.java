import java.util.HashMap;
import java.util.HashSet;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;
    private HashSet<String> speciesMails = new HashSet<>();


    public CustomerStorage()
    {
        speciesMails.add("@gmail.com");
        speciesMails.add("@mail.ru");
        speciesMails.add("@yandex.ru");
        // и так далее...

        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if (components.length != 4) throw new IllegalArgumentException("Wrong format!");
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, checkPhone(components[3]), checkMail(components[2])));
    }

    private String checkMail(String mail) {

        if (mail.contains("@"))
            if (speciesMails.contains(mail.substring(mail.indexOf("@"))))
                return mail;

        throw new IllegalArgumentException("Incorrect mail format!");
    }

    private String checkPhone(String phone) {

        String[] ph = phone.replaceAll("[^0-9]", "").split("");
        if (ph.length == 12) {
            phone = String.format("+%s(%s) %s-%s-%s", ph[0], ph[1]+ph[2]+ph[3], ph[4]+ph[5]+ph[6]+ph[7], ph[8]+ph[9], ph[10]+ph[11]);
        } else if (ph.length == 11) {
            phone = String.format("+7(%s%s%s) %s%s%s%s-%s%s-%s%s", ph[0], ph[1], ph[2], ph[3], ph[4], ph[5], ph[6], ph[7], ph[8], ph[9], ph[10]);
        } else throw new IllegalArgumentException("Incorrect format of phone!");
        return phone;
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        if (!storage.containsKey(name)) System.out.println("Wrong format!");
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}