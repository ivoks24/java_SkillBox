
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Main {

    private static String pathCopy;
    private static String initial;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Путь объекта: ");
        String pathObject = scanner.nextLine();
        System.out.print("Путь копии: ");
        pathCopy = scanner.nextLine();
        initial = pathObject.substring(pathObject.lastIndexOf('\\'));

        File file = new File(pathObject);
        if (file.isDirectory()) ifDirectory(file);
            else fileCreation(file);
    }

    private static String setPath(String s) {
        return pathCopy + s.substring(s.indexOf(initial));
    }

    private static void fileCreation(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            PrintWriter writer = new PrintWriter(setPath(file.getPath()));

            lines.forEach(line -> writer.write(line + "\n"));
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void ifDirectory(File file) {
        try {
            for (File d : file.listFiles()) {
                if (d.isDirectory()) {
                    File dir = new File(setPath(d.getPath()));
                    dir.mkdirs();
                    ifDirectory(d);
                } else {
                    fileCreation(d);
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
