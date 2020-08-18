package quick_sort;

public class QuickSort {

    public static void sort(int[] array) {

        if (array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int from, int to) {

        if (from < to) {
            int pivot = partition(array, from, to);
            sort(array, from, pivot - 1);
            sort(array, pivot + 1, to);
        }
    }

    private static int partition(int[] array, int from, int to) {

        //TODO: moving values around pivot,
        // return new pivot index

        int pivot = (to + from) / 2;
        int value = array[pivot];

        int leftMarker = from;
        int rightMarker = to;

        do {
            while (array[leftMarker] < value) {
                leftMarker++;
            }

            while (value < array[rightMarker]) {
                rightMarker--;
            }

            if (leftMarker <= rightMarker) {
                int tmp = array[leftMarker];
                array[leftMarker] = array[rightMarker];
                array[rightMarker] = tmp;

                if (leftMarker == pivot) {
                    pivot = rightMarker;
                    rightMarker++;
                } else if (rightMarker == pivot) {
                    pivot = leftMarker;
                    leftMarker--;
                }

                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        return pivot;
    }

}
