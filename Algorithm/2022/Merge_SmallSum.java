/*
 * @Author: Jihan
 * @Date: 2022-04-23 10:22:44
 * @Description: 小和问题
 * 在一个数组中，一个数左边比它小的数的总和，叫该数的小和
 * 所有数的小和累加起来，叫数组小和
 * 例子： [1,3,4,2,5] 
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16 
 * 给定一个数组arr，求数组小和
 */
public class Merge_SmallSum {
    public static int smallSum(int[] arr) {
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
        int leftIndex = L;
        int rightIndex = M + 1;
        int sum = 0;
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        while (leftIndex <= M && rightIndex <= R) {
            // 左边有几个数比当前要小
            sum += arr[leftIndex] < arr[rightIndex] ? arr[leftIndex] * (R - rightIndex + 1) : 0;
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
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 4, 2, 5 };
        System.out.println(smallSum(arr));
    }
}
