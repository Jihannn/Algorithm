/*
 * @Author: Jihan
 * @Date: 2022-07-27 23:42:57
 * @Description: https://leetcode.cn/problems/search-in-rotated-sorted-array/
 */
public class _033_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0;
        int r = nums.length - 1;
        int cur = l + ((r - l) >> 1);
        while (l <= r) {
            cur = l + ((r - l) >> 1);
            if (nums[cur] > target) {
                if (nums[cur] > nums[r] && nums[r] >= target) {
                    l = cur + 1;
                } else {
                    r = cur - 1;
                }
            } else if (nums[cur] == target) {
                return cur;
            } else {
                if (nums[cur] < nums[l] && nums[l] <= target) {
                    r = cur - 1;
                } else {
                    l = cur + 1;
                }
            }
        }
        return -1;
    }
}
