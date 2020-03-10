import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //        String path = "C:/Users/Uladzislau/Desktop";

        System.out.print("Enter the path: ");
        Scanner path = new Scanner(System.in);
        File folder = new File(path.nextLine());

        long size = 0;

        File[] files = folder.listFiles();
        List<File> folders = new ArrayList<>();

        
        for (;;) {
            if (files != null)
                for (File file : files)
                    if (file.isFile()) size = size + file.length();
                    else folders.add(file);

            if (folders.size() == 0) break;

            files = folders.remove(0).listFiles();
        }
        printSize(size);
    }

    private static void printSize(Long size) {

        String[] type = {" byte", "KB", "MG", "GB", "TB"};
        int typeUp = 0;
        double convert = size;

        while (convert > 950) {
            convert = convert / 1024;
            typeUp ++;
        }
        System.out.printf(Locale.ENGLISH, typeUp < 3 ? "%.0f%s" : "%.2f%s", convert, type[typeUp]);
    }
}
