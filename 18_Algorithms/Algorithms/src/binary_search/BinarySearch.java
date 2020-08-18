package binary_search;

import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch {

    private final ArrayList<String> list;

    public BinarySearch(ArrayList<String> list) {

        this.list = list;
        Collections.sort(this.list);
    }

    public int search(String query) {

        return search(query, 0, list.size() - 1);
    }

    private int search(String query, int from, int to)
    {
        //TODO: write code here
        int middle = (from + to) / 2;
        int result = query.compareTo(list.get(middle));

        if (to == from) {
            return -1;
        }

        if (result == 0) {
            return middle;
        } else if (result > 0) {
            if (query.compareTo(list.get(middle + 1)) == 0) {
                return middle + 1;
            }
            return search(query, middle + 1, to);
        } else {
            return search(query, from, middle);
        }
    }
}
