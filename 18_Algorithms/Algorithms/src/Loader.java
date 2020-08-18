import array_max_value.ArrayMaxValue;
import binary_search.BinarySearch;
import rabin_karp.RabinKarpExtended;

import java.util.ArrayList;
import java.util.List;

public class Loader {
    public static void main(String[] args) {

        int maxValue = ArrayMaxValue.getMaxValue(new int[]{1, 2, 3, 4, 5, 10, 23, 545, 3243, 65, 7, 32});
        System.out.println(maxValue);


//        ArrayList<String> myList = new ArrayList<>();
//        myList.add("a");
//        myList.add("A");
//        myList.add("t");
//        myList.add("h");
//        myList.add("j");
//        myList.add("b");
//        myList.add("aC");
//        myList.add("L");
//        myList.add("y");
//
//        BinarySearch binarySearch = new BinarySearch(myList);
//        System.out.println(binarySearch.search("y"));

//        String str = "Алгоритм Рабина-Карпа - это алгоритм поиска строк," +
//                " созданный Ричардом М. Карпом и Майклом О." +
//                " Рабином, который использует хэширование, " +
//                "чтобы найти какой-либо из набора строк шаблонов в тексте.";
//
//        RabinKarpExtended rabinKarpExtended = new RabinKarpExtended(str);
//        List<Integer> search = rabinKarpExtended.search("Карп");
//
//        search.forEach(System.out::println);

    }
}
