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

    public void addCustomer(String data) throws IndexOutOfBoundsException {

        String[] components = data.split("\\s+");
        if (components.length != 4) throw new IndexOutOfBoundsException("Wrong format!"); //ArrayIndexOutOfBoundsException

        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, checkPhone(components[3]), checkMail(components[2])));
    }

    private String checkMail(String mail) throws IndexOutOfBoundsException {

        if (mail.contains("@"))
            if (speciesMails.contains(mail.substring(mail.indexOf("@"))))
                return mail;

        throw new IndexOutOfBoundsException("Incorrect mail format!"); //StringIndexOutOfBoundsException
    }

    private String checkPhone(String phone) throws IndexOutOfBoundsException {

        String ph = phone.replaceAll("[^0-9]", "");
        if (ph.length() == 10) ph = "7" + ph;
        if (ph.length() == 11) phone = String.format("+%s(%s) %s-%s-%s",
                ph.substring(0, 1), ph.substring(1, 4), ph.substring(4, 8), ph.substring(8, 10), ph.substring(10));

        else throw new IndexOutOfBoundsException("Incorrect format of phone!"); //StringIndexOutOfBoundsException
        return phone;
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {

        if (!storage.containsKey(name)) System.err.println("Name is missing!");
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}