import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader {

    public static void main(String[] args) throws IOException {

        String line;
        String[] array;

        String command;
        int lineNumber = 0;

        boolean hasLineNumber;
        int count = 1;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> listOfCases = new ArrayList<>();

        System.out.println(" LIST OF CASES");

        for (;;) {
            line = reader.readLine().trim().replaceAll("\\s+", " ");
            hasLineNumber = false;

            if (line.equals("LIST") && listOfCases.size() != 0) {
                for (String list : listOfCases) {
                    System.out.println(count++ + " - " + list);
                }
                count = 1;
                continue;
            }

            array = line.split(" ", 3);
            command = array[0];

            if (array.length == 1) {
                continue;
            }

            try {
                lineNumber = Integer.parseInt(array[1]);
                if ((lineNumber <= listOfCases.size()) && (lineNumber > 0)) {
                    hasLineNumber = true;
                    lineNumber -= 1;
                }
            } catch (NumberFormatException e) {
                if (command.equals("ADD") && array.length == 2) {
                    listOfCases.add(array[1]);
                    System.out.println("- added -");
                    continue;
                }
                if (command.equals("ADD") && array.length == 3) {
                    listOfCases.add(array[1] + " " + array[2]);
                    System.out.println("- added -");
                    continue;
                }
            }

            if (array[0].equals("DELETE") && array.length == 2 && hasLineNumber) {
                listOfCases.remove(lineNumber);
                System.out.println("- deleted -");
                continue;
            }

            if (array[0].equals("EDIT") && array.length == 3 && hasLineNumber) {
                listOfCases.set(lineNumber, array[2]);
                System.out.println("- edited -");
                continue;
            }

            if (command.equals("ADD") && array.length == 3 && hasLineNumber) {
                listOfCases.add(lineNumber, array[2]);
                System.out.println("- added -");
            } else if (command.equals("ADD") && array.length == 3) {
                listOfCases.add(array[2]);
                System.out.println("- added -");
            }

        }
    }
}
