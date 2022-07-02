/*
 * @Author: Jihan
 * @Date: 2022-04-24 08:31:15
 * @Description: https://leetcode-cn.com/problems/count-of-range-sum/
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * 示例：
 * 输入：nums = [-2,5,-1], lower = -2, upper = 2
 * 输出：3
 * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 * 输入：nums = [0], lower = 0, upper = 0
 * 输出：1
 */
public class Merge_CountOfRangeSum {
    public static int countOfRangeSum(int arr[], int lower, int upper) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // long类型防止溢出
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        // 前缀和数组,sum[i]表示arr[0]~arr[i]位置的和
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {
        // merge过程中不能处理0~i位置的数，所以放到该处
        if (L == R) {
            return lower <= sum[L] && sum[L] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
                + merge(sum, L, M, R, lower, upper);
    }

    public static int merge(long[] sum, int L, int M, int R, int lower, int upper) {
        int windowL = L;
        int windowR = L;
        int count = 0;
        // arr[i]~arr[j]等于sum[j] - sum[i-1]
        // 算出每个以i为结尾的前缀和，即可算出整体的前缀和
        // 如果j位置的sum为x，arr[i]~arr[j]要符合在[lower,upper]之间，则sum[i-1]要在[x-upper,x-lower]之间
        for (int i = M + 1; i <= R; i++) {
            long newLower = sum[i] - upper;
            long newUpper = sum[i] - lower;
            while (windowR <= M && sum[windowR] <= newUpper) {
                windowR++;
            }
            while (windowL <= M && sum[windowL] < newLower) {
                windowL++;
            }
            count += windowR - windowL;
        }
        long[] help = new long[R - L + 1];
        int helpIndex = 0;
        int leftIndex = L;
        int rightIndex = M + 1;
        while (leftIndex <= M && rightIndex <= R) {
            help[helpIndex++] = sum[leftIndex] < sum[rightIndex] ? sum[leftIndex++] : sum[rightIndex++];
        }
        while (leftIndex <= M) {
            help[helpIndex++] = sum[leftIndex++];
        }
        while (rightIndex <= R) {
            help[helpIndex++] = sum[rightIndex++];
        }
        for (int i = 0; i < help.length; i++) {
            sum[L + i] = help[i];
        }
        return count;
    }
}