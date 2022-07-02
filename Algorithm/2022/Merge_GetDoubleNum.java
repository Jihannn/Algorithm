/*
 * @Author: Jihan
 * @Date: 2022-04-23 11:17:04
 * @Description: LeetCode-493
 * 在一个数组中，对于任何一个数num，求有多少个(后面的数*2)依然<num，返回总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 */
public class Merge_GetDoubleNum {
    public static int getDoubleNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int windowR = M + 1;
        int count = 0;
        for (int i = L; i <= M; i++) {
            // 如果不越界并且两倍仍小于num，则往右滑
            while (windowR <= R && (arr[windowR] << 1) < arr[i]) {
                windowR++;
            }
            count += windowR - (M + 1);
        }
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        int leftIndex = L;
        int rightIndex = M + 1;
        while (leftIndex <= M && rightIndex <= R) {
            help[helpIndex++] = arr[leftIndex] < arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        while (leftIndex <= M) {
            help[helpIndex++] = arr[leftIndex++];
        }
        while (rightIndex <= R) {
            help[helpIndex++] = arr[rightIndex++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 1, 7, 0, 2 };
        System.out.println(getDoubleNum(arr));
    }
}