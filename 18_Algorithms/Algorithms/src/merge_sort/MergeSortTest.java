package merge_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MergeSortTest {

    @Test
    public void mergeSort() {

        int [] expected = {1, 15, 4, 3, 7, 8, 2, 10, 11, 9, 6, 5};
        int [] actual = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15};
        MergeSort.mergeSort(expected);
        System.out.println(Arrays.toString(expected));

        Assert.assertArrayEquals(expected, actual);
    }
}
