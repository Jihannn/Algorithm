/*
 * @Author: Jihan
 * @Date: 2022-07-31 22:47:05
 * @Description: https://leetcode.cn/problems/squares-of-a-sorted-array/
 */
public class _977_SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int N = nums.length;
        int[] res = new int[N];
        int resIndex = N - 1;
        int left = 0;
        int right = N - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                res[resIndex--] = nums[left] * nums[left];
                left++;
            } else {
                res[resIndex--] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }
}