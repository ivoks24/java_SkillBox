import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SecondVersion {

    static int countRegionCode = 80;
    static char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(new Generation(1, 81));

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }



    static class Generation extends RecursiveTask<Integer> {

        int fromRegionCode;
        int toRegionCode;

        public Generation(int fromRegionCode, int toRegionCode) {
            this.fromRegionCode = fromRegionCode;
            this.toRegionCode = toRegionCode;
        }

        @Override
        protected Integer compute() {

            if (toRegionCode - fromRegionCode <= countRegionCode / 4) {

                FileOutputStream writer = null;
                try {
                    writer = new FileOutputStream("res/regionCodeFrom" + fromRegionCode + ".txt");
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
                return toRegionCode;
            } else {
                int middle = (toRegionCode - fromRegionCode) / 2;
                Generation firstHalf = new Generation(fromRegionCode, middle);
                firstHalf.fork();
                Generation secondHalf = new Generation(middle + 1, toRegionCode);
                secondHalf.fork();
//                int secondValue = secondHalf.compute();
                return firstHalf.join();
            }
        }

        private static void padNumber(StringBuffer buffer, int number, int numberLength) {

            int padSize = numberLength - Integer.toString(number).length();
            for (int i = 0; i < padSize; i++) {
                buffer.append('0');
            }
            buffer.append(number);
        }
    }
}
