import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter your phone number:");
        String[] PH = reader.readLine().replaceAll("[^0-9]", "").split("");

        if (PH.length == 12) {
            System.out.println("+" + PH[0] + " (" + PH[1] + PH[2] + PH[3] + ") " + PH[4] + PH[5] + PH[6] + PH[7] + "-" + PH[8] + PH[9] + "-" + PH[10] + PH[11]);
        } else if (PH.length == 11) {
            System.out.println("+7 (" + PH[0] + PH[1] + PH[2] + ") " + PH[3] + PH[4] + PH[5] + PH[6] + "-" + PH[7] + PH[8] + "-" + PH[9] + PH[10]);
        } else {
            System.out.println("Error");
        }

    }
}
