/*
 * @Author: Jihan
 * @Date: 2022-04-20 21:08:13
 * @Description: 求解任意局部最小的位置 
 * 1.第0位比第1位的数小则第0位为局部最小 
 * 2.第n位比第n-1位的数小则第n位为局部最小 
 * 3.其它位置的数比其相邻的数小，则该位为局部最小
 * 4.任意相邻的两个数不相等
 */

public class LocalMinimum {
    public static int localMinimum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int result = -1;
        if (arr[L] < arr[L + 1]) {
            return L;
        }
        if (arr[R] < arr[R - 1]) {
            return R;
        }
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[] = { 15, 7, 5, 0, 1, 4, -2, 7, 12 };
        System.out.println(localMinimum(arr));
    }
}