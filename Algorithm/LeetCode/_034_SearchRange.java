/*
 * @Author: Jihan
 * @Date: 2022-07-27 23:40:08
 * @Description: https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class _034_SearchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || (nums.length == 1 && nums[0] != target)) {
            return new int[] { -1, -1 };
        }
        int start = -1;
        int end = -1;
        int rightIndex = -1;
        int l = 0;
        int r = nums.length - 1;
        int mid = l + ((r - l) >> 1);
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                if (start == -1) {
                    if (mid - 1 == -1 || (mid - 1 > 0 && nums[mid - 1] != target)) {
                        start = mid;
                        end = mid;
                        l = rightIndex == -1 ? 0 : rightIndex;
                        r = nums.length - 1;
                    } else {
                        rightIndex = rightIndex == -1 ? mid : rightIndex;
                        r = mid - 1;
                    }
                } else {
                    if (mid + 1 == nums.length || (mid + 1 < nums.length && nums[mid + 1] != target)) {
                        end = mid;
                        break;
                    } else {
                        l = mid + 1;
                    }
                }
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return new int[] { start, end };
    }
}
