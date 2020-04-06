import java.io.File;

public class Main {

    private static int newWidth = 300;

    public static void main(String[] args) {

        String srcFolder = "C:/Users/Uladzislau/Pictures/Photos/Uladzislau";
        String dstFolder = "C:/Users/Uladzislau/Desktop/dst";

        File srcDir = new File(srcFolder);

        int cores = Runtime.getRuntime().availableProcessors();
        long start = System.currentTimeMillis();

        File[] allFiles = srcDir.listFiles();
        int middle = allFiles.length / cores;
        int size = middle;

        for (int i = 0; i < cores; i++) {

            if (i == cores - 1) size = allFiles.length - middle * (cores - 1);

            File[] files = new File[size];
            System.arraycopy(allFiles, middle * i, files, 0, size);
            ImagesResizer resize = new ImagesResizer(files, newWidth, dstFolder, start);
            new Thread(resize).start();
        }
    }
}
