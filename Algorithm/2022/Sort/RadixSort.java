package Sort;
/*
 * @Author: Jihan
 * @Date: 2022-04-28 11:45:45
 * @Description: 基数排序
 */
public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, getMaxDigit(arr,0,arr.length-1));
    }

    public static void radixSort(int[] arr, int L, int R, int digit) {
        for (int i = 1; i <= digit; i++) {
            int[] count = new int[10];
            int[] help = new int[R - L + 1];
            // 所有数当前digit出现的次数
            for (int j = L; j <= R; j++) {
                count[getDigit(arr[j], i)]++;
            }
            // 前缀累加和，得到每个数位置的范围
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }
            // 复制到辅助数组指定位置
            for (int j = R; j >= L; j--) {
                help[--count[getDigit(arr[j], i)]] = arr[j];
            }
            // 按当前digit排好序，复制回原数组
            for (int j = 0; j < help.length; j++) {
                arr[j + L] = help[j];
            }
        }
    }

    public static int getMaxDigit(int[] arr,int L,int R) {
        int maxDigit = 0;
        int maxNum = Integer.MIN_VALUE;
        for (int i = L; i <= R; i++) {
            maxNum = Math.max(maxNum, arr[i]);
        }
        while (maxNum != 0) {
            maxDigit++;
            maxNum /= 10;
        }
        return maxDigit;
    }

    public static int getDigit(int num, int digit) {
        return ((num / (int) Math.pow(10, digit - 1)) % 10);
    }

    public static void printArr(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if(i != arr.length - 1){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] arr = {73,2,40,638,9,123,56,79534,62,0,464};
        radixSort(arr);
        printArr(arr);
    }
}
