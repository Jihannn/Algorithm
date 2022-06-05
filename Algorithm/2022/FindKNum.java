import java.util.Arrays;

import Sort.QuickSort;

/*
 * @Author: Jihan
 * @Date: 2022-06-05 21:57:42
 * @Description: 在无序数组中找到第K小的数
 */
public class FindKNum {

    // O(N^2)
    public static int findK1(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k >= arr.length || k < 0) {
            return -1;
        }
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        insertSort(copyArr, 0, copyArr.length - 1);
        return copyArr[k - 1];
    }

    // O(N*logN)
    public static int findK2(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k >= arr.length || k < 0) {
            return -1;
        }
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        QuickSort.quickSort(copyArr);
        return copyArr[k - 1];
    }

    // O(N)
    public static int findK3(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k >= arr.length || k < 0) {
            return -1;
        }
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        return quickSort(copyArr, k - 1);
    }

    // O(N)
    public static int findK4(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k >= arr.length || k < 0) {
            return -1;
        }
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        return bfprt(copyArr, 0, arr.length - 1, k - 1);
    }

    private static int bfprt(int[] arr, int L, int R, int K) {
        if (L == R) {
            return arr[L];
        }
        int pivot = medianOfMedian(arr, L, R);
        int[] equals = partition(arr, L, R, pivot);
        if (equals[0] <= K && K <= equals[1]) {
            return arr[K];
        } else if (K < equals[0]) {
            return bfprt(arr, L, equals[0] - 1, K);
        } else {
            return bfprt(arr, equals[1] + 1, R, K);
        }
    }

    private static int medianOfMedian(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] medianArr = new int[size / 5 + offset];
        for (int team = 0; team < medianArr.length; team++) {
            int teamFirst = L + team * 5;
            medianArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        return bfprt(medianArr, 0, medianArr.length - 1, medianArr.length / 2);
    }

    private static int getMedian(int[] arr, int L, int R) {
        insertSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    private static int quickSort(int[] arr, int k) {
        return process(arr, 0, arr.length - 1, k);
    }

    private static int process(int[] arr, int L, int R, int K) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] equals = partition(arr, L, R, pivot);
        if (equals[0] <= K && K <= equals[1]) {
            return arr[K];
        } else if (K < equals[0]) {
            return process(arr, L, equals[0] - 1, K);
        } else {
            return process(arr, equals[1] + 1, R, K);
        }
    }

    private static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] == pivot) {
                cur++;
            } else {
                swap(arr, --more, cur);
            }
        }
        return new int[] { less + 1, more - 1 };
    }

    private static void insertSort(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = L + 1; i <= R; i++) {
            for (int j = i; j - 1 >= L && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 100;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int randomK = 1 + (int)(Math.random() * arr.length - 1);
            int rtn1 = findK1(arr, randomK);
            int rtn2 = findK2(arr, randomK);
            int rtn3 = findK3(arr, randomK);
            int rtn4 = findK4(arr, randomK);
            if (rtn1 != rtn2 || rtn2 != rtn3 || rtn3 != rtn4) {
                System.out.println("error");
                printArray(arr);
                System.out.println(rtn1);
                System.out.println(rtn2);
                System.out.println(rtn3);
                System.out.println(rtn4);
                System.out.println("====");
                break;
            }
        }
    }
}