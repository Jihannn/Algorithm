public class SelectionSort {

    public static void selectSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                minIndex = array[j] < array[minIndex] ? j : minIndex;
            }
            swap(array, i, minIndex);
        }
    }

    public static void selectMaxSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = array.length - 1; i > 0; i--) {
            int maxNum = i;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[maxNum]) {
                    maxNum = j;
                }
            }
            swap(array, i, maxNum);
        }
    }

    public static void swap(int array[], int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void printArray(int array[]) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                System.out.print("[");
            } else if (i == array.length - 1) {
                System.out.print(array[i] + "]");
                break;
            }
            System.out.print(array[i] + ",");
        }
    }

    public static void main(String[] args) {
        int array[] = { 0, 5, 3, 7, 5, 4 };
        selectSort(array);
        printArray(array);
    }
}
