import java.io.File;

public class Main {

    private static int newWidth = 300;

    public static void main(String[] args) {

        String srcFolder = "C:/Users/Uladzislau/Pictures/Photos/Uladzislau";
        String dstFolder = "C:/Users/Uladzislau/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int middle = files.length / 4;

        File[] files1 = new File[middle];
        System.arraycopy(files, 0, files1, 0, files1.length);
        ImagesResizer resizer1 = new ImagesResizer(files1, newWidth, dstFolder, start);
        new Thread(resizer1).start();

        File[] files2 = new File[middle];
        System.arraycopy(files, middle, files2, 0, files2.length);
        ImagesResizer resizer2 = new ImagesResizer(files2, newWidth, dstFolder, start);
        new Thread(resizer2).start();

        File[] files3 = new File[middle];
        System.arraycopy(files, middle * 2, files3, 0, files3.length);
        ImagesResizer resizer3 = new ImagesResizer(files3, newWidth, dstFolder, start);
        new Thread(resizer3).start();

        File[] files4 = new File[files.length - middle * 3];
        System.arraycopy(files, middle * 3, files4, 0, files4.length);
        ImagesResizer resizer4 = new ImagesResizer(files4, newWidth, dstFolder, start);
        new Thread(resizer4).start();


    }
}
