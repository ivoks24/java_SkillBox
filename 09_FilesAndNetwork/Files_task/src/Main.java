import java.io.*;
import java.util.*;

public class Main {

    private static long size;
    private static List<File> folders = new ArrayList<>();
    private static File[] files;

    public static void main(String[] args) {

        System.out.print("Enter the path: ");
//        folders.addAll(Collections.singleton(new File(((new Scanner(System.in)).nextLine()))));
//        не совсем понял как работает singleton, по-этому переписал по-другому

        files = (new File(((new Scanner(System.in)).nextLine()))).listFiles();
        if (files != null) {
            Collections.addAll(folders, files);
            operation(folders.remove(0));
        }

        printSize(size);
    }

    private static void operation(File folder) {

        files = folder.listFiles();

        if (files != null)
            for (File file : files)
                if (file.isFile()) size = size + file.length();
                else folders.add(file);

        if (!(folders.size() == 0)) operation(folders.remove(0));
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
