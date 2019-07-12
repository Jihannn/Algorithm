package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/6/29
 */
public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeProcess(arr, 0, arr.length - 1);
    }

    private static void mergeProcess(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }

        int mid = L + (R - L) / 2;
        mergeProcess(arr, L, mid);
        mergeProcess(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int p = 0, p1 = L, p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[p++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[p++] = arr[p1++];
        }
        while (p2 <= R) {
            help[p++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
}
