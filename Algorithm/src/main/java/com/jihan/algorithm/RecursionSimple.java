package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/6/27
 */
public class RecursionSimple {

    public static int getMax(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }

        int mid = (L + R) / 2;
        int left = getMax(arr, L, mid);
        int right = getMax(arr, mid + 1, R);

        return left >= right ? left : right;
    }
}
