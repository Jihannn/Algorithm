package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/6/27
 */
public class InsertSort {
    public static void InsertSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        for(int i = 1 ; i < arr.length ; i++){
            for(int j = i ; j > 0 && arr[j] < arr[j-1] ; j--){
                swap(arr,j,j-1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
