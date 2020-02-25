import java.util.*;

public class Loader {
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        generator(arrayList);

        TreeSet<String> treeList = new TreeSet<>();
        treeList.addAll(arrayList);

        HashSet<String> hashList = new HashSet<>();
        hashList.addAll(arrayList);

        Scanner scanner = new Scanner(System.in);
        String number;
        long start;
        long duration;
        boolean availability;

        Collections.sort(arrayList);

        for (;;) {
            System.out.print("Enter the car number: ");
            number = scanner.nextLine();

            start = System.nanoTime();
            availability = arrayList.contains(number);
            duration = System.nanoTime() - start;
            if (availability) {
                System.out.println("\nПоиск перебором: номер найден, поиск занял " + duration + "нс.");
            } else {
                System.out.println("\nПоиск перебором: номер не найден, поиск занял " + duration + "нс.");
            }

            start = System.nanoTime();
            int index = Collections.binarySearch(arrayList, number);
            duration = System.nanoTime() - start;
            if (index > -1) {
                System.out.println("Бинарный поиск: номер найден, поиск занял " + duration + "нс.");
            } else {
                System.out.println("Бинарный поиск: номер не найден, поиск занял " + duration + "нс.");
            }

            start = System.nanoTime();
            availability = treeList.contains(number);
            duration = System.nanoTime() - start;
            if (availability) {
                System.out.println("Поиск по TreeSet: номер найден, поиск занял " + duration + "нс.");
            } else {
                System.out.println("Поиск по TreeSet: номер не найден, поиск занял " + duration + "нс.");
            }

            start = System.nanoTime();
            availability = hashList.contains(number);
            duration = System.nanoTime() - start;
            if (availability) {
                System.out.println("Поиск по HashSet: номер найден, поиск занял " + duration + "нс.\n");
            } else {
                System.out.println("Поиск по HashSet: номер не найден, поиск занял " + duration + "нс.\n");
            }

        }
    }

    private static void generator(ArrayList<String> list) {

        String[] letters = {"A", "B", "C", "E", "H", "K", "M", "O", "P", "T", "Y"};

        for (int i = 0; i < 10; i++) {
            for (String letter1 : letters) {
                for (String letter2 : letters) {
                    for (String letter3 : letters) {
                        for (int j = 1; j < 200; j++) {

                            list.add(letter1 + i + i + i + letter2 + letter3 + j); //A333AA197
                        }
                    }
                }
            }
        }
    }
}
