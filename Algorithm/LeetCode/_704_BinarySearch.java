/*
 * @Author: Jihan
 * @Date: 2022-07-31 22:09:39
 * @Description: https://leetcode.cn/problems/binary-search/
 */
public class _704_BinarySearch {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1 || target < nums[0]) {
            return -1;
        }
        int res = -1;
        int N = nums.length;
        int left = 0;
        int right = N - 1;
        int mid = left + ((right - left) >> 1);
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                res = mid;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
}
