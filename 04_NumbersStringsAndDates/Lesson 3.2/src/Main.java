import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int boxes = Integer.parseInt(reader.readLine());

        int BOXES_IN_THE_CONTAINER = 5;
        int BOXES_IN_THE_TRUCK = 2 * BOXES_IN_THE_CONTAINER;

        boolean writeContainer = false;
        boolean writeTruck = false;

        for (int i = 1; i <= boxes; i++) {

            if (writeTruck || i == 1) {

                System.out.println("Truck " + (i/BOXES_IN_THE_TRUCK + 1) + ":");
                writeTruck = false;
            }

            if (writeContainer || i == 1) {

                System.out.println("\t container " + (i/BOXES_IN_THE_CONTAINER + 1) + ":");
                writeContainer = false;
            }

            System.out.println("\t\t box " + i);

            if (i % BOXES_IN_THE_TRUCK == 0) {
                writeTruck = true;
            }

            if (i % BOXES_IN_THE_CONTAINER == 0) {
                writeContainer = true;
            }
        }
    }
} // 1 грузовик - 12 контейнеров по 27 ящиков
