package merge_sort;

public class MergeSort
{
    public static void mergeSort(int[] array)
    {
        int n = array.length;
        if(n < 2) {
            return;
        }
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] left, int[] right)
    {
        //TODO
        int markLeftArray = 0, markRightArray = 0;
        int rightLength = right.length, leftLength = left.length;

        for (int i = 0; i < array.length; i++) {

            if (markLeftArray == leftLength) {
                array[i] = right[markRightArray];
                markRightArray++;
            } else if (markRightArray == rightLength) {
                array[i] = left[markLeftArray];
                markLeftArray++;
            } else if (left[markLeftArray] >= right[markRightArray]) {
                array[i] = right[markRightArray];
                markRightArray++;
            } else if (left[markLeftArray] < right[markRightArray]) {
                array[i] = left[markLeftArray];
                markLeftArray++;
            }
        }
    }
}
