package quick_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuickSortTest {

    @Test
    public void sort() {

        int [] expected = {1, 112, 3, 10, 6, 9, 111, 115, 2, 4, 7};
        int [] actual = {1, 2, 3, 4, 6, 7, 9, 10, 111, 112, 115};
        QuickSort.sort(expected);

        System.out.println(Arrays.toString(expected));

        Assert.assertArrayEquals(expected, actual);
    }
}
