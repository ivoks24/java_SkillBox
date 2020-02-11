import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите ФИО: ");
        String FIO = reader.readLine().trim();

        if (FIO.indexOf(' ') == -1)
        {
            System.out.println("Фамилия: " + FIO);
        } else {
            System.out.println("Фамилия: " + FIO.substring(0, FIO.indexOf(' ')));
            FIO = FIO.substring(FIO.indexOf(' ')).trim();

            if (FIO.indexOf(' ') == -1) {
                System.out.println("Имя: " + FIO);
            } else {
                System.out.println("Имя: " + FIO.substring(0, FIO.indexOf(' ')));
                FIO = FIO.substring(FIO.indexOf(' ')).trim();

                if (FIO.indexOf(' ') == -1) {
                    System.out.println("Отчество: " + FIO);
                } else {
                    System.out.println("Отчество: " + FIO.substring(0, FIO.indexOf(' ')));
                }
            }
        }
    }
}
