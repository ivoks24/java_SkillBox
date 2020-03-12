import java.io.*;
import java.util.*;

public class Main {

    private static long size;
    private static List<File> folders = new ArrayList<>();

    public static void main(String[] args) {

        System.out.print("Enter the path: ");
        folders.addAll(Collections.singleton(new File(((new Scanner(System.in)).nextLine()))));
        folders.forEach(Main::ifDirectory);

        printSize(size);
    }

    private static void ifDirectory(File file) {

        try {
            if (file.isDirectory()) {
                for (File e : file.listFiles()) {
                    ifDirectory(e);
                }
            } else {
                size += file.length();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
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
