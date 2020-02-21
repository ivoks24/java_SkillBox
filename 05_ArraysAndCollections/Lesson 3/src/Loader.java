import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Loader {
    public static void main(String[] args) throws IOException {

        HashSet<String> speciesMails = new HashSet<>();

        speciesMails.add("@gmail.com");
        speciesMails.add("@mail.ru");
        speciesMails.add("@yandex.ru");
        // и так далее...

        HashSet<String> addedMails = new HashSet<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (;;) {
            System.out.println("type the command: ");
            String mail = reader.readLine().trim().replaceAll("\\s+", " ");
            String[] division = mail.split("\\s");

            if (mail.equals("LIST")) {
                for (String mails : addedMails) {
                    System.out.println(mails);
                }
                continue;
            }

            if (division.length != 2) {
                System.out.println(" - error - ");
                continue;
            }

            if (division[0].equals("ADD") && speciesMails.contains(division[1].substring(division[1].indexOf("@")))) {
                addedMails.add(division[1]);
                System.out.println(" - added - ");
            } else {
                System.out.println(" - error - ");
            }
        }
    }
}
