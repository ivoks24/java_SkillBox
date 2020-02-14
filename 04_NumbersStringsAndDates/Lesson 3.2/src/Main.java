import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int boxes = Integer.parseInt(reader.readLine());

        int trucks = (int)Math.ceil((double) boxes/12/27);
        int containers = (int)Math.ceil((double) boxes/27);

        for (int i = 1; i <= trucks; i++) {
            System.out.println("Грузовик " + i + ":");
            int restCont = 0; int lastTruck = i;

            if (trucks == i) {
                restCont = containers%12;
                lastTruck = lastTruck - 1;
            }

            for (int k = 1 + (i - 1)*12; k <= lastTruck*12 + restCont; k++) {
                System.out.println("Контейнер " + k + ":");
                int restBoxes = 0; int lastCont = k;

                if (containers == k) {
                    restBoxes = boxes%27;
                    lastCont = lastCont - 1;
                }
                for (int b = 1 + (k - 1)*27; b <= lastCont*27 + restBoxes; b++) {
                    System.out.println("Ящик " + b);
                }
            }
        }
    }
} // 1 грузовик - 12 контейнеров по 27 ящиков
