import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        int CONTAINERS_IN_THE_TRUCK = 12;
        int BOXES_IN_THE_CONTAINER = 27;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int boxes = Integer.parseInt(reader.readLine());

        int ACTUAL_TRUCK = 1;
        int ACTUAL_CONTAINER = 1;
        int ACTUAL_BOX = 0;

        do {
            System.out.println("Truck " + ACTUAL_TRUCK++ + ":");

            for (int j = 1; j <= CONTAINERS_IN_THE_TRUCK; j++) {
                System.out.println("\t" + "Container " + ACTUAL_CONTAINER++ + ":");

                for (int k = 1; k <= BOXES_IN_THE_CONTAINER; k++) {
                    System.out.println("\t\t" + "Box " + ++ACTUAL_BOX);

                    if (ACTUAL_BOX == boxes) break;
                }
                if (ACTUAL_BOX == boxes) break;
            }
        } while (ACTUAL_BOX != boxes);
    }
}
