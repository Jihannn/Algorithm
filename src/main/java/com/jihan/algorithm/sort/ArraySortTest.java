package com.jihan.algorithm.sort;

import java.util.Arrays;

/**
 * Created by Jihan on 2019/6/27
 */
public class ArraySortTest {
    public static void main(String[] args) {
        checkCorrect();
    }

    private static void checkCorrect() {
        int[] arr = randomArray(100, 100);
        int[] arrFalse = arr.clone();
        int[] arrTrue = arr.clone();
        int testTime = 100000;
        boolean success = true;

        for (int i = 0; i < testTime; i++) {
//            BubbleSort.bubbleSort(arr);
//            MergeSort.mergeSort(arr);
//            QuickSort.quickSort(arr);
            HeapSort.heapSort(arrFalse);
            Arrays.sort(arrTrue);

            if (!isEquals(arrFalse, arrTrue)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "success" : "failed | " +
                "\n arr     :" + Arrays.toString(arr) +
                "\n arrFail :" + Arrays.toString(arrFalse) +
                "\n arrTrue :" + Arrays.toString(arrTrue));
    }

    private static boolean isEquals(int[] arr, int[] arrCopy) {
        if (arr == null || arrCopy == null || arr.length != arrCopy.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arrCopy[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] randomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random() - (maxValue + 1) * Math.random());
        }
        return arr;
    }
}
