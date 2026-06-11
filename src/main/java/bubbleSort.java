public class bubbleSort {
    void bubbleSorter(int[] array) {
        int temp;
        boolean exchanged = true;
        int size = array.length;

        for (int i = 0; i < size && exchanged; i++) {
            exchanged = false;
            for (int j = size - 1; j >= i + 1; j--) {
                if (array[j] < array[j-1]) {
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    exchanged = true;
                }
            }
        }
    }
}