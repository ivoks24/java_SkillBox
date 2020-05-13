import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Loader {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        AtomicInteger countThread = new AtomicInteger(0);

        FileOutputStream writer = new FileOutputStream("res/numbers.txt");
        FileOutputStream writer2 = new FileOutputStream("res/numbers2.txt");
        FileOutputStream writer3 = new FileOutputStream("res/numbers3.txt");
        FileOutputStream writer4 = new FileOutputStream("res/numbers4.txt");

        ArrayList<FileOutputStream> files = new ArrayList<>();

        files.add(writer);
        files.add(writer2);
        files.add(writer3);
        files.add(writer4);

        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        for (int number = 1; number < 1000; number++) {

            StringBuffer buffer = new StringBuffer();

            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        for (int regionCode = 1; regionCode < 50; regionCode++) {

                            buffer.append(firstLetter);
                            padNumber(buffer, number, 3);
                            buffer.append(secondLetter);
                            buffer.append(thirdLetter);
                            padNumber(buffer, regionCode, 2);
                            buffer.append('\n');
                        }
                    }
                }
            }
            countThread.incrementAndGet();
            executorService.submit(new Thread(() -> {
                for (FileOutputStream file : files) {
                    try {
                        file.write(buffer.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                countThread.decrementAndGet();
            }));
//            buffer.setLength(0);
        }

        while (countThread.get() != 0) {
            Thread.sleep(50);
        }

        for (FileOutputStream file : files) {
            file.flush();
            file.close();
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static void padNumber(StringBuffer buffer, int number, int numberLength) {

        int padSize = numberLength - Integer.toString(number).length();
        for(int i = 0; i < padSize; i++) {
            buffer.append('0');
        }

        buffer.append(number);
    }
}
