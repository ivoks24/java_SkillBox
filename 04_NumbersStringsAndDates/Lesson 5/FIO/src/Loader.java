import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter your name: ");
        String[] FIO = reader.readLine().replaceAll("^[A-Z[a-z]А-Я[a-я]]\\s+", "")
                .trim().replaceAll("\\s+", " ").split(" ");

        if (FIO.length > 2) {
            System.out.println("Фамилия: " + FIO[0] + "\n" + "Имя: " + FIO[1] + "\n" + "Отчество: " + FIO[2]);
        } else if (FIO.length == 2) {
            System.out.println("Фамилия: " + FIO[0] + "\n" + "Имя: " + FIO[1]);
        } else if (FIO.length == 1) {
            System.out.println("Фамилия: " + FIO[0]);
        }
    }
}
