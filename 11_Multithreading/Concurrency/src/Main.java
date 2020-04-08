import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) {

        Callable<Double> callable = (() -> {

            double sum = 0;
            for (int i = 0; i < 1000; i++) {
                sum += Math.random();
            }

            if (sum / 1000 < 0.5) {
                throw new IllegalArgumentException("Error!");
            }
            return sum / 1000;
        });

        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException | IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
