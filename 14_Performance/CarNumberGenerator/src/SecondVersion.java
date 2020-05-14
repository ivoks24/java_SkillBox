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
        pool.invoke(new Generation(1, countRegionCode + 1, countRegionCode / 4));

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }



    static class Generation extends RecursiveTask<Void> {

        private final int fromRegionCode;
        private int toRegionCode;
        private final int minCountOfRegion;

        public Generation(int fromRegionCode, int toRegionCode, int minCountOfRegion) {
            this.fromRegionCode = fromRegionCode;
            this.toRegionCode = toRegionCode;
            this.minCountOfRegion = minCountOfRegion;
        }

        @Override
        protected Void compute() {

            Generation generation = null;
            if (toRegionCode - fromRegionCode > minCountOfRegion) {
                int nextFromRegionCode = fromRegionCode + minCountOfRegion;
                generation = new Generation(nextFromRegionCode, toRegionCode, minCountOfRegion);
                generation.fork();
                toRegionCode = nextFromRegionCode;
            }


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
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (generation != null) {
                generation.join();
            }
            return null;
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
