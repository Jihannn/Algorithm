/*
 * @Author: Jihan
 * @Date: 2022-05-29 17:07:22
 * @Description: https://leetcode.cn/problems/sum-of-subarray-minimums/
 */
public class SumOfSubarrayMinimums {
    public int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] left = getLeftLessAndEqual(arr);
        int[] right = getRightLess(arr);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += (arr[i] * ((i - left[i]) * (right[i] - i)));
            ans %= 1000000007;
        }
        return (int) ans;
    }

    private int[] getLeftLessAndEqual(int[] arr) {
        int[] left = new int[arr.length];
        int[] stack = new int[arr.length];
        int size = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            while (size != -1 && arr[i] <= arr[stack[size]]) {
                left[stack[size--]] = i;
            }
            stack[++size] = i;
        }
        while (size != -1) {
            left[stack[size--]] = -1;
        }
        return left;
    }

    private int[] getRightLess(int[] arr) {
        int[] right = new int[arr.length];
        int[] stack = new int[arr.length];
        int size = -1;
        for (int i = 0; i < arr.length; i++) {
            while (size != -1 && arr[i] < arr[stack[size]]) {
                right[stack[size--]] = i;
            }
            stack[++size] = i;
        }
        while (size != -1) {
            right[stack[size--]] = arr.length;
        }
        return right;
    }
}