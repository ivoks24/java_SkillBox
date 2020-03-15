
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Путь объекта: "); // C:/Users/Uladzislau/Desktop/files
        Path originalPath = Paths.get(scanner.nextLine().trim());
//        System.out.println(originalPath.isAbsolute());

        System.out.print("Путь копии: "); // C:/Users/Uladzislau/Desktop/copy
        Path copyPath = Paths.get(scanner.nextLine().trim() + "/" + originalPath.getFileName());

        CopyingDirectory visitor = new CopyingDirectory(originalPath, copyPath);
        try {
            Files.walkFileTree(originalPath, visitor);
        } catch (Exception ex) {
            System.err.println("No such file!");
        }
    }
}
