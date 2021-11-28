/*
 * @Author: Jihan
 * @Date: 2021-09-16 14:22:10
 * @Description: 基数排序
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixProcess(arr, 0, arr.length - 1, getDigtial(arr));
    }

    // 获取数组中的最长的位数
    private static int getDigtial(int[] arr) {
        int num = 0;
        int maxNum = Integer.MIN_VALUE;
        for (int i : arr) {
            maxNum = Math.max(i, maxNum);
        }
        while (maxNum != 0) {
            num++;
            maxNum /= 10;
        }
        return num;
    }

    private static void radixProcess(int[] arr, int L, int R, int maxDig) {
        // 因为是十进制,基数为10
        final int RADIX = 10;
        // 辅助数组
        int[] bucket = new int[R - L + 1];
        // 从个位数到最大位数的优先级排序
        for (int i = 1; i <= maxDig; i++) {
            // 每个位数的数量
            int[] count = new int[RADIX];
            // 当前位数所处位置
            for (int j = L; j <= R; j++) {
                count[getDigNum(arr[j], i)]++;
            }
            // 前方词频总和，由此可知每个数字前方都有多少个数字，可直接定位到下标
            for (int j = 1; j < RADIX; j++) {
                count[j] += count[j - 1];
            }
            // 逆序(符合后进后出)输出到对应位置
            for (int j = R; j >= L; j--) {
                bucket[--count[getDigNum(arr[j], i)]] = arr[j];
            }
            int bCur = 0, aCur = L;
            // 把辅助数组中排好序的复制到原数组
            while (aCur <= R) {
                arr[aCur++] = bucket[bCur++];
            }
        }
    }

    // 获取数位的数字
    private static int getDigNum(int num, int dig) {
        return (num / (int) Math.pow(10, dig - 1)) % 10;
    }

    public static void swap(int list[], int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public static void printList(int list[]) {
        for (int i = 0; i < list.length; i++) {
            if (i == 0) {
                System.out.print("[");
            } else if (i == list.length - 1) {
                System.out.print(list[i] + "]");
                break;
            }
            System.out.print(list[i] + ",");
        }
    }

    public static void main(String[] args) {
        int[] arr = { 8, 7, 6, 4, 16, 15, 11, 5, 7, 8, 23, 4 };
        radixSort(arr);
        printList(arr);
    }
}
