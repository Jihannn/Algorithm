public class MergeSort {

    public static void mergeSort(int array[]) {
        if (array == null || array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    private static void process(int array[], int L, int R) {
        // 如果只有一个元素则结束递归
        if (L == R) {
            return;
        }
        int M = L + (R - L >> 1);
        // 左右分治递归
        process(array, L, M);
        process(array, M + 1, R);
        merge(array, L, M, R);
    }

    private static void merge(int array[], int L, int M, int R) {
        // 临时数组存放排序好的子序列
        int[] tempArray = new int[R - L + 1];
        int left = L;
        int right = M + 1;
        int cur = 0;
        // 把L-R中的数递增放入临时数组
        while (left <= M && right <= R) {
            tempArray[cur++] = array[left] <= array[right] ? array[left++] : array[right++];
        }
        // 如果右边已遍历完则把左边剩余放入临时数组
        while (left <= M) {
            tempArray[cur++] = array[left++];
        }
        // 如果左边已遍历完则把右边剩余放入临时数组
        while (right <= R) {
            tempArray[cur++] = array[right++];
        }
        // 把排序好的结果覆盖原数组
        for (int i = 0; i < tempArray.length; i++) {
            array[L + i] = tempArray[i];
        }
    }

    // 小和问题，数组中一个数右侧比它大的数的总和
    public static int smallSum(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        return smallSumProcess(array, 0, array.length - 1);
    }

    private static int smallSumProcess(int[] array, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + (R - L >> 1);
        return smallSumProcess(array, L, M) + smallSumProcess(array, M + 1, R) + smallSumMerge(array, L, M, R);
    }

    private static int smallSumMerge(int[] array, int L, int M, int R) {
        int[] tempArray = new int[R - L + 1];
        int left = L;
        int right = M + 1;
        int cur = 0;
        int sum = 0;
        while (left <= M && right <= R) {
            // 如果相等则右指针先移动,否则无法计算左侧小和
            sum += array[left] < array[right] ? array[left] * (R - right + 1) : 0;
            tempArray[cur++] = array[left] < array[right] ? array[left++] : array[right++];
        }
        while (left <= M) {
            tempArray[cur++] = array[left++];
        }
        while (right <= R) {
            tempArray[cur++] = array[right++];
        }
        for (int i = 0; i < tempArray.length; i++) {
            array[L + i] = tempArray[i];
        }
        return sum;
    }

    // 打印逆序对 数组左边比右边大为一个逆序对
    public static void printReverseOrder(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        reverseProcess(array, 0, array.length - 1);
    }

    private static void reverseProcess(int[] array, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        reverseProcess(array, L, M);
        reverseProcess(array, M + 1, R);
        reverseMerge(array, L, M, R);
    }

    private static void reverseMerge(int[] array, int L, int M, int R) {
        int temparray[] = new int[R - L + 1];
        int left = L;
        int right = M + 1;
        int cur = 0;
        while (left <= M && right <= R) {
            // 因为两个子序列都有序递增，当left大于right，那么left~M的数都和right成为逆序对。
            if (array[left] > array[right]) {
                for (int i = left; i <= M; i++) {
                    System.out.print("[" + array[i] + "," + array[right] + "]");
                }
                System.out.println();
            }
            temparray[cur++] = array[left] < array[right] ? array[left++] : array[right++];
        }
        while (left <= M) {
            temparray[cur++] = array[left++];
        }
        while (right <= R) {
            temparray[cur++] = array[right++];
        }
        for (int i = 0; i < temparray.length; i++) {
            array[L + i] = temparray[i];
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
        System.out.println();
    }

    public static void main(String[] args) {
        int array[] = { 5, 7, 8, 4, 3, 2, 1, 7, 4 };
        mergeSort(array);
        printArray(array);
        int sumarray[] = { 1, 2, 3, 4, 5, 6 }; // 5+8+9+8+5 = 35
        System.out.println(smallSum(sumarray));
        int reverseOrderarray[] = { 7, 5, 0, 4, 3 };
        printReverseOrder(reverseOrderarray);
    }
}
