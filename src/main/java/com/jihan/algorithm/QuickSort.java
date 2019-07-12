package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/7/1
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr, R, L + (int) ((R - L + 1) * Math.random()));
            int[] equals = partition(arr, L, R);
            quickSort(arr, L, equals[0] - 1);
            quickSort(arr, equals[0] + 1, R);
        }
    }

    private static int[] partition(int[] arr, int L, int R) {

        int left = L - 1;
        int right = R;
        int index = L;

        while (index < right) {
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++left);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --right);
            } else {
                index++;
            }
        }

        swap(arr, right++, R);

        return new int[]{left + 1, right - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
