/*
 * @Author: Jihan
 * @Date: 2022-07-31 23:05:24
 * @Description: https://leetcode.cn/problems/minimum-size-subarray-sum/
 */
public class _209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (left <= right && right < nums.length) {
            sum += nums[right++];
            while (sum >= target) {
                res = Math.min(res, right - left);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
