package bubble_sort;

import org.junit.Assert;
import org.junit.Test;

public class BubbleSortTest {

    @Test
    public void sort() {

        int [] expected = {1, 3, 6, 9, 2, 4, 7};
        int [] actual = {1, 2, 3, 4, 6, 7, 9};
        BubbleSort.sort(expected);

        Assert.assertArrayEquals(expected, actual);
    }
}
