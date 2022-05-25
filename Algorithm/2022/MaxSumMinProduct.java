import java.util.ArrayDeque;
import java.util.LinkedList;

/*
 * @Author: Jihan
 * @Date: 2022-05-25 09:50:32
 * @Description: https://leetcode.cn/problems/maximum-subarray-min-product/
 * 一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。
 * 比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
 * 给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。
 * 请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。
 * 子数组 定义为一个数组的 连续 部分。
 */
public class MaxSumMinProduct {
    public static int force(int[] arr) {
        long max = 0;
        for (int i = 0; i < arr.length; i++) {
            long min = arr[i];
            long count = 0;
            for (int j = i; j < arr.length; j++) {
                min = Math.min(min, arr[j]);
                count += arr[j];
                max = Math.max(max, min * count);
            }
        }
        return (int) (max % (long) (Math.pow(10, 9) + 7));
    }

    public static int maxSumMinProduct(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int N = nums.length;
        // 累加和O(N)
        long[] sum = new long[N];
        sum[0] = nums[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        // 查找以i为最小值的范围
        int[][] indexRange = new int[N][2];
        ArrayDeque<LinkedList<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek().peekLast()]) {
                LinkedList<Integer> set = stack.pop();
                if (nums[i] == nums[set.peekLast()]) {
                    set.addLast(i);
                    stack.push(set);
                    break;
                } else {
                    for (Integer index : set) {
                        indexRange[index][0] = stack.isEmpty() ? -1 : stack.peek().peekLast();
                        indexRange[index][1] = i;
                    }
                }
            }
            if (stack.isEmpty() || nums[i] != nums[stack.peek().peekLast()]) {
                LinkedList<Integer> set = new LinkedList<>();
                set.add(i);
                stack.push(set);
            }
        }
        while (!stack.isEmpty()) {
            LinkedList<Integer> set = stack.pop();
            for (Integer index : set) {
                indexRange[index][0] = stack.isEmpty() ? -1 : stack.peek().peekLast();
                indexRange[index][1] = -1;
            }
        }
        long max = 0;
        for (int i = 0; i < indexRange.length; i++) {
            int min = nums[i];
            int left = indexRange[i][0] == -1 ? 0 : indexRange[i][0] + 1;
            int right = indexRange[i][1] == -1 ? N - 1 : indexRange[i][1] - 1;
            long rangeSum = left == 0 ? sum[right] : sum[right] - sum[left - 1];
            max = Math.max(max, min * rangeSum);
        }
        return (int) (max % (long) (Math.pow(10, 9) + 7));
    }

    public static void main(String[] args) {
        int[] arr = { 2, 5, 4, 2, 4, 5, 3, 1, 2, 4 };
        System.out.println(force(arr));
        System.out.println(maxSumMinProduct(arr));
    }
}