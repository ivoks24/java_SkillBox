import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainVersion {

    static int countThrow = 4;
    static int countRegion = 100;
    static ExecutorService executorService = Executors.newFixedThreadPool(countThrow);
    static char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        generator(1, countRegion + 1);

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static void generator(int fromRegionCode, int toRegionCode) {

        if (toRegionCode - fromRegionCode == countRegion / countThrow) {

            executorService.submit(new Thread(() -> {

                FileOutputStream writer = null;
                try {
                    writer = new FileOutputStream(
                            "res/regionCode[" + fromRegionCode + ", " + toRegionCode + "].txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                for (int number = 1; number < 1000; number++) {

                    StringBuffer buffer = new StringBuffer();

                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                for (int regionCode = fromRegionCode; regionCode < toRegionCode; regionCode++) {

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
                    try {
                        writer.write(buffer.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));

        } else {
            int middle = (fromRegionCode + toRegionCode) / 2;
            generator(fromRegionCode, middle);
            generator(middle, toRegionCode);
        }
    }

    private static void padNumber(StringBuffer buffer, int number, int numberLength) {

        int padSize = numberLength - Integer.toString(number).length();
        for(int i = 0; i < padSize; i++) {
            buffer.append('0');
        }

        buffer.append(number);
    }
}
