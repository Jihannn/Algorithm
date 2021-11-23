public class InsertionSort {

    // 直接插入排序
    public static void insertionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }
    }

    // 哨兵插入排序
    public static void sentryInsertionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        // 第一个位置留给哨兵
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 1, array.length);
        for (int i = 2; i < newArray.length; i++) {
            // 如果前面已经有序则不遍历
            if (newArray[i] < newArray[i - 1]) {
                // 当前位置赋值给哨兵
                newArray[0] = newArray[i];
                int j = i - 1;
                // 相比直接插入少一次比较
                for (; newArray[j] > newArray[0]; j--) {
                    swap(newArray, j, j + 1);
                }
                newArray[j + 1] = newArray[0];
            }
        }
        // 赋值回原数组
        System.arraycopy(newArray, 1, array, 0, array.length);
    }

    // 折半插入排序
    public static void halfInsertionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 1, array.length);
        for (int i = 2; i < newArray.length; i++) {
            int low = 1;
            int high = i - 1;
            newArray[0] = newArray[i];
            while (low <= high) {
                int mid = (low + high) / 2;
                if (newArray[0] < newArray[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 最后插入位置为high + 1
            for (int j = i - 1; j >= high + 1; j--) {
                newArray[j + 1] = newArray[j];
            }
            newArray[high + 1] = newArray[0];
        }
        System.arraycopy(newArray, 1, array, 0, array.length);
    }

    // 希尔排序
    public static void shellSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int step = 1;
        while (step < array.length / 3) {
            step = 3 * step + 1;
        }
        while (step >= 1) {
            for (int i = step; i < array.length; i++) {
                for (int j = i; j - step >= 0 && array[j] < array[j - step]; j -= step) {
                    swap(array, j, j - step);
                }
            }
            step /= 3;
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
        int[] array1 = { 0, 6, 1, 3, 8, 4, 0, 3 };
        insertionSort(array1);
        printArray(array1);
        int[] array2 = { 0, 6, 1, 3, 8, 4, 0, 3 };
        InsertionSort.sentryInsertionSort(array2);
        printArray(array2);
        int[] array3 = { 0, 6, 1, 3, 8, 4, 0, 3 };
        InsertionSort.halfInsertionSort(array3);
        printArray(array3);
        int[] array4 = { 0, 6, 1, 3, 8, 4, 0, 3 };
        InsertionSort.shellSort(array4);
        printArray(array4);
    }
}