import java.util.ArrayList;

public class Main {

    private static ArrayList<Double> numbers = new ArrayList<>();

    public static void main(String[] args) {

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(Main::someHeaveMethod));
        }
        threads.forEach(Thread::start);
    }

    private static void someHeaveMethod() {

        for (int i = 0; i < 1_000_000; i++) {

            double value = Math.random() / Math.random();
            synchronized (Main.class) {
                numbers.add(value);
            }
        }
        System.out.println(numbers.size());
        numbers.clear();
    }
}
